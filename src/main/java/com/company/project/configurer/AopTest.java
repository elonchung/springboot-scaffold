package com.company.project.configurer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class AopTest {

    private final Logger logger = LoggerFactory.getLogger(AopTest.class);

//    @Pointcut("@annotation()")
//    public void pcut(){
//        logger.info("111",);
//        log.warn();
//    }
}
