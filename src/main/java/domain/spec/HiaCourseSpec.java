package domain.spec;

import domain.entity.HiaCourseEntity;
import domain.entity.HiaStudentEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * Created by ohseoklee on 2018-11-29.
 * specification for course
 */
public class HiaCourseSpec {
    public static Specification<HiaCourseEntity> taked(final String studentNum) {
        return new Specification<HiaCourseEntity>() {
            @Override
            public Predicate toPredicate(Root<HiaCourseEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join<HiaStudentEntity, HiaCourseSpec> students = root.join("hiaStudentEntity");

                return criteriaBuilder.like(students.get("studentNum"), "%" + studentNum + "%");
            }
        };
    }
}
