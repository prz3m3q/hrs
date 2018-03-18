package pl.com.bottega.hrs.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

@Component
@Aspect
public class HandleTimerAspect {

    @Around("execution(* handle(..)) && this(pl.com.bottega.hrs.application.Handler)")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        long startTime = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long endTime = System.currentTimeMillis();
        // stop stopwatch
        String log = String.format("Where: %s Total execution time: %d ms", pjp.getSignature().toString(), (endTime - startTime));
        System.out.println(log);
        Logger.getLogger(HandleTimerAspect.class).info(log);
        return retVal;
    }

}
