package controller;

import domain.entity.HiaSemesterEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.InfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ohseoklee on 2018-12-05.
 *
 * several information
 */

@RestController
@RequestMapping("/info")
public class InfoController {

    @Resource
    private InfoService infoService;

    @RequestMapping("/semesters")
    public List<HiaSemesterEntity> getSemesterInfo() {
        return infoService.getSemesterInfo();
    }
}
