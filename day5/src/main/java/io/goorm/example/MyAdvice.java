package io.goorm.example;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MyAdvice {
    StopWatch stopWatch = new StopWatch();
    //@Before("execution(* my.aoptest.*.*(..))")
   @Before("execution(* my.aoptest.Hello.test*(..))")
   public void before()
   {
       System.out.println("before.... time");
       stopWatch.start();
   }
   @After("execution(* my.aoptest.Hello.test*(..))")
   public void after()
   {
        System.out.println("after");
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());       
   }
    
	// @Around("execution (* my.aoptest.*.*(..))")
	// public Object time(ProceedingJoinPoint pjp) {
		 
	// 	System.out.println("around start");
	// 	Object result = null;
	// 	try {
	// //...
	// 		result=pjp.proceed(); // testA()
	// 		System.out.println("result="+result);
	// //...
	// 	}catch (Throwable e) {
	// 		e.printStackTrace();
	// 	}
	// 	System.out.println("around end");
	// 	return result;
	//  }    
}
