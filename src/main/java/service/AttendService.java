package service;

import constant.AttendStatus;
import domain.entity.HiaAttendanceEntity;
import domain.entity.HiaCourseEntity;
import domain.entity.HiaCourseTimeEntity;
import domain.entity.HiaStudentEntity;
import domain.repo.HiaAttendanceRepository;
import domain.repo.HiaCourseRepository;
import domain.repo.HiaStudentEntityRepository;
import org.hibernate.Hibernate;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import request.AttendRequest;
import response.attendance.AttendResponse;
import util.DateConverter;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by ohseoklee on 2018-12-04.
 * service for attend
 */

@Service
public class AttendService {

    @Resource
    private HiaCourseRepository hiaCourseRepository;

    @Resource
    private HiaStudentEntityRepository studentEntityRepository;

    @Resource
    private HiaAttendanceRepository hiaAttendanceRepository;

    @Transactional
    public AttendResponse attend(AttendRequest request) {
        AttendResponse attendResponse = new AttendResponse();

        Logger.getLogger(AttendService.class).info(request.toString());

        HiaStudentEntity hiaStudentEntity = studentEntityRepository.findOneByStudentNum(request.getStudentNum());
        HiaCourseEntity hiaCourseEntity = hiaCourseRepository.findByStudents_StudentNumAndCourseTimes_DayAndCourseTimes_Time(
                request.getStudentNum(), request.getCourseInfo().getDay(), request.getCourseInfo().getTime()
        );


        // check if it is duplicate
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.set(Calendar.DAY_OF_MONTH, tomorrow.get(Calendar.DAY_OF_MONTH) + 1);


        HiaAttendanceEntity duplicatedAttend =
                hiaAttendanceRepository.findStudentAttendCourseIdAndDateBetween(
                        hiaStudentEntity.getId(),
                        hiaCourseEntity.getId(),
                        new Date(today.getTimeInMillis()),
                        new Date(tomorrow.getTimeInMillis())
                );

        if (duplicatedAttend != null) {
            attendResponse.setAttendStatus(AttendStatus.ALREADY);
            return attendResponse;
        }


        HiaAttendanceEntity entity = new HiaAttendanceEntity();

        entity.setHiaStudentByStudentId(hiaStudentEntity);
        entity.setHiaCourseByCourseId(hiaCourseEntity);
        entity.setSemesterId(1);
        entity.setAttendAt(new Timestamp(request.getAttendAt().getTime()));


        Hibernate.initialize(hiaCourseEntity.getCourseTimes());
        HiaCourseTimeEntity courseTimeEntity = hiaCourseEntity.getCourseTimes().get(0);

        long leftTime = DateConverter.getCalendarFormCourseTime(courseTimeEntity).getTimeInMillis() - request.getAttendAt().getTime();

        if (leftTime < 1000 * 60 * 5) {
            attendResponse.setAttendStatus(AttendStatus.LATENESS);
        } else {
            attendResponse.setAttendStatus(AttendStatus.ATTENDANCE);
        }

        entity.setStatus(attendResponse.getAttendStatus());

        hiaAttendanceRepository.save(entity);

        return attendResponse;
    }

}
