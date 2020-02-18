package service;

import domain.entity.*;
import domain.repo.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import request.AuthenticationRequest;
import response.AuthResponse;
import security.AuthInfo;
import security.AuthRole;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by ohseoklee on 2018-11-17.
 * security service
 */

@Service
public class SecurityService {

    private final long TOKEN_ALIVE_TERM;

    @Resource
    private HiaAuthenticationRepository authenticationRepository;

    @Resource
    private HiaStudentEntityRepository studentEntityRepository;

    @Resource
    private HiaAdminEntityRepository adminEntityRepository;

    @Resource
    private HiaProfessorEntityRepository professorEntityRepository;

    @Resource
    private HiaTerminalRepository hiaTerminalRepository;

    public SecurityService() {
        TOKEN_ALIVE_TERM = 1000 * 60 * 60 * 2; // 1000 (ms) * 60 (s) * 60 (m) * 2 (h)
    }

    public boolean isValidatedToken(String token) {

        HiaAuthenticationEntity authenticationEntity = authenticationRepository.findOneByToken(token);

        if (authenticationEntity != null) {
            if (authenticationEntity.getExpiredAt().getTime() > new Date().getTime()) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public AuthResponse authenticate(String id, String password, AuthRole type) {
        AuthResponse authResponse = null;
        boolean isAuthenticated = false;

        switch (type) {
            case ROLE_ADMIN:
                HiaAdminEntity adminEntity = adminEntityRepository.findOneByUsername(id);
                if (adminEntity != null)
                    isAuthenticated = BCrypt.checkpw(password, adminEntity.getPassword());
                break;
            case ROLE_PROFESSOR:
                HiaProfessorEntity professorEntity = professorEntityRepository.findOneByUsername(id);
                if (professorEntity != null)
                    isAuthenticated = BCrypt.checkpw(password, professorEntity.getPassword());
                break;
            case ROLE_STUDENT:
                HiaStudentEntity studentEntity = studentEntityRepository.findOneByStudentNum(id);
                if (studentEntity != null)
                    isAuthenticated = BCrypt.checkpw(password, studentEntity.getPassword());
                break;
            case ROLE_DEVICE:
                HiaTerminalEntity hiaTerminalEntity = hiaTerminalRepository.findOneByName(id);
                if (hiaTerminalEntity != null)
                    isAuthenticated = hiaTerminalEntity.getUuid().equals(password);
                break;
            default:
        }

        System.out.println("인증 됨? " + isAuthenticated);
        
        if (isAuthenticated) {
            long nowTime = new Date().getTime();

            HiaAuthenticationEntity authenticationEntity = authenticationRepository.findOneByUsername(id);

            if (authenticationEntity != null) {
                return new AuthResponse(
                        authenticationEntity.getToken(),
                        new Timestamp(nowTime),
                        new Timestamp(nowTime + TOKEN_ALIVE_TERM),
                        id
                );
            }

            authResponse = new AuthResponse(
                    BCrypt.hashpw(id + password, BCrypt.gensalt()),
                    new Timestamp(nowTime),
                    new Timestamp(nowTime + TOKEN_ALIVE_TERM),
                    id
            );

            authenticationEntity = new HiaAuthenticationEntity();

            authenticationEntity.setCreatedAt(authResponse.getCreatedAt());
            authenticationEntity.setExpiredAt(authResponse.getExpiredAt());
            authenticationEntity.setToken(authResponse.getToken());
            authenticationEntity.setRole(type);
            authenticationEntity.setUsername(authResponse.getUsername());

            authenticationRepository.save(authenticationEntity);
        }

        return authResponse;
    }

    @Transactional
    public void storeAuthInfo(String token) {
        HiaAuthenticationEntity authenticationEntity = authenticationRepository.findOneByToken(token);

        AuthInfo authInfo = AuthInfo.getInstance();
        authInfo.init(authenticationEntity);
    }

    public AuthResponse authenticate(AuthenticationRequest request) {
        return this.authenticate(request.getId(), request.getPassword(), request.getRole());
    }
}
