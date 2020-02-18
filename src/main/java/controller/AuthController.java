package controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import request.AuthenticationRequest;
import response.AuthResponse;
import response.ErrorResponse;
import service.SecurityService;

import javax.annotation.Resource;

/**
 * Created by ohseoklee on 2018-11-17.
 *
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private SecurityService securityService;

    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse authError() {
        return new ErrorResponse(401, "Authentication Required");
    }

    @RequestMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthenticationRequest request) {
        return securityService.authenticate(request);
    }
}
