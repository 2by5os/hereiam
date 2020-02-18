package service;

import constant.AttendStatus;
import domain.entity.*;
import domain.repo.HiaAttendanceRepository;
import domain.repo.HiaCourseRepository;
import domain.repo.HiaCourseTimeRelationRepository;
import domain.repo.HiaStudentEntityRepository;
import domain.repo.SemesterRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.Professor;
import response.Semester;
import response.Student;
import response.attendance.AttendHistory;
import response.attendance.AttendInfo;
import response.course.CourseInfo;
import response.course.NextCourseInfo;
import response.schedule.ClassSchedule;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by ohseoklee on 2018. 11. 11..
 */

@Service
public class StudentService {

    @Resource
    private HiaStudentEntityRepository studentEntityRepository;

    @Resource
    private SemesterRepository semesterRepository;

    @Resource
    private HiaCourseRepository hiaCourseRepository;

    @Resource
    private HiaAttendanceRepository hiaAttendanceRepository;

    @Resource
    private HiaCourseTimeRelationRepository courseTimeRelationRepository;

    @Transactional
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        for (HiaStudentEntity hiaStudentEntity : studentEntityRepository.findAll()) {
            Student student = new Student();
            student.setId(hiaStudentEntity.getId());
            student.setFacultyId(hiaStudentEntity.getFacultyId());
            student.setName(hiaStudentEntity.getName());
            student.setStudentNum(hiaStudentEntity.getStudentNum());

            students.add(student);
        }

        return students;
    }

    @Transactional
    public ClassSchedule getSchedule(String studentNum) {
        ClassSchedule schedule = new ClassSchedule();
        HiaStudentEntity hiaStudentEntity = studentEntityRepository.findOneByStudentNum(studentNum);

        Hibernate.initialize(hiaStudentEntity.getTakedCourses());

        List<HiaCourseEntity> courses = hiaStudentEntity.getTakedCourses();


        for (HiaCourseEntity course : courses) {
            Hibernate.initialize(course.getCourseTimes());
            for (HiaCourseTimeEntity courseTime : course.getCourseTimes()) {
                schedule.setSchedule(courseTime.getDay(), courseTime.getTime(), course.getTitle());
            }
        }

        return schedule;
    }

    @Transactional
    public NextCourseInfo getNextCourse(String studentNum) {
        Calendar calendar = Calendar.getInstance();

        int period = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        
        final int orgDay = day;
        final int orgPeriod = period;
        final int endDay = 7;
        final int endPeriod = 24;

        HiaCourseEntity hiaCourseEntity = null;
        NextCourseInfo courseInfo = new NextCourseInfo();

        System.out.println(day + " : " + period);
        loop:
        for (; day <= endDay; day++) {
            for (; period <= endPeriod; period++) {
            	System.out.println("\t" + day + " 날 " + period + " 시간  검색");
                hiaCourseEntity = hiaCourseRepository.findByStudents_StudentNumAndCourseTimes_DayAndCourseTimes_Time(studentNum, day, period);
                if (hiaCourseEntity != null) {
                	System.out.println("\t\t 검색 성공");
                	break loop;
                }
            }
            period = 1;
        }

        if (hiaCourseEntity == null) {
            courseInfo.init(null, NextCourseInfo.STATUS.NONE);
        } else {
            Hibernate.initialize(hiaCourseEntity.getClassroom());
            Hibernate.initialize(hiaCourseEntity.getClassroom().getDevice());
            
            CourseInfo course = new CourseInfo(
                    hiaCourseEntity.getTitle(),
                    hiaCourseEntity.getClassroom().getName(),
                    day,
                    period
            );
            
            course.setCourseId(hiaCourseEntity.getId());
            course.setEndTime(period + courseTimeRelationRepository.countByCourseId(course.getCourseId()));

            course.setDeviceAddr(hiaCourseEntity.getClassroom().getDevice().getBluetoothAddress());

            courseInfo.setCourseInfo(course);

            System.out.println(orgDay + " : " + day + " // " + orgPeriod + " : " + period);

            courseInfo.setStatus(
                    (orgDay == day && orgPeriod == period) ? NextCourseInfo.STATUS.CUR : NextCourseInfo.STATUS.NEXT
            );
        }

        return courseInfo;
    }

    @Transactional
    public AttendHistory getAttendHistory(String studentNum, Integer courseId, Integer semesterId) {
        AttendHistory attendHistory = new AttendHistory();

        HiaSemesterEntity semesterEntity = semesterRepository.findOne(semesterId);
        HiaStudentEntity student = studentEntityRepository.findOneByStudentNum(studentNum);
        HiaCourseEntity courseEntity = hiaCourseRepository.findOne(courseId);

        Hibernate.initialize(courseEntity.getCourseTimes());

        List<HiaCourseTimeEntity> courseTimes = courseEntity.getCourseTimes();

        List<HiaAttendanceEntity> attendances =
                hiaAttendanceRepository.findAllByStudentAttendCourseIdAndDateBetween(
                        student.getId(),
                        courseId,
                        semesterEntity.getBeginAt(),
                        semesterEntity.getEndAt()
                );

        List<AttendInfo> attendInfos = new ArrayList<>();


        // 학기 시작일로부터 첫 강의 날자 구하기
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(semesterEntity.getBeginAt().getTime());

        HiaCourseTimeEntity firstTime = courseTimes.get(0);

        if (start.get(Calendar.DAY_OF_WEEK) < (firstTime.getDay() + 1)) {
            start.add(Calendar.DATE, 7);
        }
        
        System.out.println(start.getTimeInMillis());

        start.set(Calendar.DAY_OF_WEEK, (firstTime.getDay() + 1));
        
        System.out.println(start.getTimeInMillis());

        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(semesterEntity.getEndAt().getTime());

        HiaAttendanceEntity aItem = null;
        

        int week = 1;
        // 일주일마다 루프를 돌려 강의 주차별 출석 정보 기록
        // 출석 정보를 하나씩 들고와 해당 날에 출석 정보가 있는지 확인 후 처리.

        Iterator<HiaAttendanceEntity> attendIterator = attendances.iterator();
        while (compare(start, end)) {

            if (aItem == null && attendIterator.hasNext()) {
                aItem = attendIterator.next();
                System.out.println(aItem);
            }

            if (aItem != null && isSameDay(aItem.getAttendAt(), start.getTime())) {
                attendInfos.add(new AttendInfo(aItem, week));
                aItem = null;
            } else {
                attendInfos.add(new AttendInfo(AttendStatus.ABSENCE, start.getTimeInMillis(), week));
            }

            start.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH) + 7);
            week++;
        }

        attendHistory.setAttendInfos(attendInfos);
        attendHistory.setSemester(
                new Semester(
                        semesterEntity.getId(),
                        semesterEntity.getYear(),
                        semesterEntity.getSemester(),
                        semesterEntity.getBeginAt(),
                        semesterEntity.getEndAt()
                )
        );

        return attendHistory;
    }

    private boolean isSameDay(Timestamp attendAt, Date time) {
        if (attendAt == null || time == null) return false;

        Calendar left = Calendar.getInstance();
        left.setTimeInMillis(attendAt.getTime());
        Calendar right = Calendar.getInstance();
        right.setTime(time);

        boolean isSameYear = (left.get(Calendar.YEAR) == right.get(Calendar.YEAR));
        boolean isSameDayOfYear = (left.get(Calendar.DAY_OF_YEAR) == right.get(Calendar.DAY_OF_YEAR));

        return isSameYear && isSameDayOfYear;
    }

    public boolean compare(Calendar a, Calendar b) {
        return a.getTimeInMillis() < b.getTimeInMillis();
    }

    @Transactional
    public List<CourseInfo> getTakedCourses(String studentNum) {
        List<CourseInfo> courseInfos = new ArrayList<>();

        List<HiaCourseEntity> courseEntities = hiaCourseRepository.findByStudents_StudentNum(studentNum);

        for (HiaCourseEntity courseEntity : courseEntities) {
            Hibernate.initialize(courseEntity.getCourseTimes());
            Hibernate.initialize(courseEntity.getClassroom());
            Hibernate.initialize(courseEntity.getProfessor());

            List<HiaCourseTimeEntity> courseTimeEntities = courseEntity.getCourseTimes();

            HiaCourseTimeEntity firstTime = courseTimeEntities.get(0);
            HiaCourseTimeEntity endTime = courseTimeEntities.get(courseTimeEntities.size() - 1);

            HiaClassroomEntity classroomEntity = courseEntity.getClassroom();

            CourseInfo courseInfo = new CourseInfo(
                    courseEntity.getTitle(),
                    classroomEntity.getName(),
                    firstTime.getDay(),
                    firstTime.getTime()
            );

            courseInfo.setCourseId(courseEntity.getId());

            courseInfo.setEndTime(endTime.getTime());

            HiaProfessorEntity professorEntity = courseEntity.getProfessor();

            courseInfo.setProfessor(
                    new Professor(
                            professorEntity.getId(),
                            professorEntity.getName()
                    )
            );

            courseInfos.add(courseInfo);
        }

        return courseInfos;
    }
}
