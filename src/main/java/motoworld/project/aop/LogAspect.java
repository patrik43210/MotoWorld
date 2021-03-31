package motoworld.project.aop;


import motoworld.project.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* motoworld.project.web.ViewController.motorcycleDetails(..))")
    public void detailsPointcut(){};

    @After("detailsPointcut()")
    public void afterAdvice(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        String motorcycleId = (String) args[0];
        String action = joinPoint.getSignature().getName();

        logService.createLog(action, motorcycleId);
    }
}
