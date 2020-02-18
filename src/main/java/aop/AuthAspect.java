package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import security.AuthInfo;


/**
 * Created by ohseoklee on 2018-11-21.
 * authenticating aop
 */

@Aspect
@Component
public class AuthAspect {

    @Pointcut("@annotation(security.PreAuth)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeTargetMethod(JoinPoint joinPoint) {
        AuthInfo authInfo = AuthInfo.getInstance();
        String type = joinPoint.getSignature().getDeclaringTypeName();

        System.out.println("------------------------------");
        if (type.contains("role")) {
            System.out.println(joinPoint.getSignature().getName());
        }
        System.out.println("------------------------------");
    }
}
