package service;

import domain.entity.HiaSemesterEntity;
import domain.repo.SemesterRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ohseoklee on 2018-12-05.
 */


@Service
public class InfoService {
    @Resource
    private SemesterRepository semesterRepository;

    public List<HiaSemesterEntity> getSemesterInfo() {
        return semesterRepository.findAll();
    }
}
