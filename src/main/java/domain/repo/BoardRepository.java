package domain.repo;

import constant.BOARD_TYPE;
import domain.entity.HiaBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018-12-05.
 *
 */
@Repository
public interface BoardRepository extends JpaRepository<HiaBoardEntity, Integer> {
    Page<HiaBoardEntity> findAllByType(BOARD_TYPE board_type, Pageable pageable);
}
