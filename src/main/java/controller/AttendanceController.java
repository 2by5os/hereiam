package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import request.AttendRequest;
import response.attendance.AttendResponse;
import service.AttendService;

import javax.annotation.Resource;

/**
 * Created by ohseoklee on 2018-12-04.
 * controller for attendance
 */


@RestController
@RequestMapping("/attend")
public class AttendanceController {

    @Resource
    private AttendService attendService;

    @RequestMapping(method = RequestMethod.POST)
    public AttendResponse attend(@RequestBody AttendRequest request) {
        try {
            return attendService.attend(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new AttendResponse();
        }
    }
}
