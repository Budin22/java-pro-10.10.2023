package ua.goals.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

//    @Pointcut("execution(* ua.ithillel.tripplanner.service..*.*(..))")
//    private void serviceMethod(){}
//
//    @Around(value = "serviceMethod() || repoMethod() || controllerMethod()")
//    public  Object logAroundMethod(ProceedingJoinPoint pjp) throws Throwable {
//        Signature signature = pjp.getSignature();
//        log.debug("Calling {} with args: {}", signature, Arrays.toString(pjp.getArgs()));
//
//        Object res = pjp.proceed();
//        log.debug("Method {} returned {}", pjp.getSignature(), res);
//
//        return res;
//    }
//
//    @AfterThrowing(value = "serviceMethod() || repoMethod()", throwing = "e")
//    public void logMethodAfterThrown(Exception e){
//        log.error("Exception in service method: {}", e.getMessage());
//    }
}
