package achraf.aspectj.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import java.util.Arrays;


@Aspect
public class SecurityAspect {

    @Around("@annotation(securedBy)")
    public Object checkSecurity(ProceedingJoinPoint joinPoint, SecuredBy securedBy) throws Throwable {
        String[] requiredRoles = securedBy.roles();
        if (requiredRoles.length == 0) {
            return joinPoint.proceed();
        }
        SecurityContext securityContext = SecurityContext.getInstance();
        if (securityContext.getCurrentUsername() == null) {
            throw new SecurityException("User is not authenticated");
        }
        if (!securityContext.hasAnyRole(requiredRoles)) {
            throw new SecurityException("User does not have required roles. Required: " +
                    Arrays.toString(requiredRoles));
        }
        return joinPoint.proceed();
    }
}
