package com.xiaoye.iworks.common.logger.aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 日志AOP处理类
 * @Pointcut定义一个方法为切点里面的内容为一个表达式,下面详细介绍<br>
 * 
 * @Before 在切点前执行方法,内容为指定的切点<br>
 * @Around 环绕切点,在进入切点前,跟切点后执行<br>
 * @After 在切点后,return前执行,<br>
 * @AfterReturning 在切入点,return后执行,如果想对某些方法的返回参数进行处理,可以在这操作<br>
 * @AfterThrowing 在切点后抛出异常进行处理<br>
 * @order(i) 标记切点的优先级,i越小,优先级越高<br>
 * @author yehl
 * @date 2018年4月21日 上午10:11:42
 */
@Aspect
@Component
public class RecordLoggerAspect implements Ordered {

	/*
	 * 1)JoinPoint
	 *  java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表；  
	 *  Signature getSignature()：获取连接点的方法签名对象；
	 *  java.lang.Object getTarget()：获取连接点所在的目标对象；
	 *  java.lang.Object getThis() ：获取代理对象本身；
	 *  
	 * 2)ProceedingJoinPoint
	 *  ProceedingJoinPoint继承JoinPoint子接口，它新增了两个用于执行连接点方法的方法： 
	 *   java.lang.Object proceed() throws java.lang.Throwable：通过反射执行目标对象的连接点处的方法； 
	 *   java.lang.Object proceed(java.lang.Object[] args) throws java.lang.Throwable：通过反射执行目标对象连接点处的方法，不过使用新的入参替换原来的入参。
	 *   
	 *   @winthin 拦截类上面的注解（）
	 *   @annotation 拦截方法上的注解
	*/
	
	protected final Log LOGGER = LogFactory.getLog(getClass());

	protected int order = -100000;
	
	@Before("@annotation(com.xiaoye.iworks.common.logger.annotation.RecordLogger)")
	public void executeBefore(JoinPoint joinPoint) {
		/**
		 * 目标方法执行前执行<br>
		 * @Around 环绕注解在执行joinPoint.proceed()时执行<br>
		 * 所以，@Around 方法中 joinPoint.proceed() 方法执行之前的动作先于本方法执行
		 */
	}

	@Around("@annotation(com.xiaoye.iworks.common.logger.annotation.RecordLogger)")
	public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object object;
		ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
		HttpServletRequest request = requestAttributes.getRequest();
		String url = "";
		if(request != null) {
			url = request.getRequestURI();
		}
		RecordLogger recordLogger = getRecordLogger(joinPoint);
		if(recordLogger.inputLogger()) {
			LOGGER.info(String.format("request: %s | input: %s", url, JSON.toJSONString(this.getArgs(joinPoint))));
		}
		if(recordLogger.recordInput()) {
			// TODO
		}
		// 记录方法执行前日志
		object = joinPoint.proceed(); // 执行目标方法
		// 记录方法执行后日志
		if(recordLogger.resultLogger()) {
			LOGGER.info(String.format("request: %s | result: %s", url, JSON.toJSONString(object)));
		}
		if(recordLogger.recordResult()) {
			// TODO
		}
		return object;
	}

	@After("@annotation(com.xiaoye.iworks.common.logger.annotation.RecordLogger)")
	public void executeAfter(JoinPoint joinPoint) {
		/**
		 * 目标方法执行后执行<br>
		 * @Around 方法执行后执行<br>
		 * 也就是说：如果有环绕执行的方法（@Around），则在环绕方法执行后在执行<br>
		 * 不管目标方法有没有抛出异常，本方法都会执行
		 */
	}

	@AfterReturning(pointcut = "@annotation(com.xiaoye.iworks.common.logger.annotation.RecordLogger)", returning = "rtv")
	public void executeAfterReturning(JoinPoint joinPoint, Object rtv) {
		/**
		 * 目标方法返回后执行<br>
		 * @After 方法执行后执行<br>
		 * 目标方法抛出异常后，本方法不执行
		 * rtv : 目标方法返回值
		 */
	}

	@AfterThrowing("@annotation(com.xiaoye.iworks.common.logger.annotation.RecordLogger)")
	public void executeAfterThrowing(JoinPoint joinPoint) {
		/**
		 * 目标方法抛出异常时执行<br>
		 * @After 方法执行后执行<br>
		 */
		if(getRecordLogger(joinPoint) != null) {
			LOGGER.debug("start record before method execute…………");
			// 执行异常日志 TODO
		}
	}

	/**
	 * @see Ordered#getOrder()
	 */
	@Override
	public int getOrder() {
		return order;
	}

	/**
	 * 判断方法上是否有日志注解
	 * @param joinPoint
	 * @return
	 */
	private RecordLogger getRecordLogger(JoinPoint joinPoint) {
		if(joinPoint == null) return null;
		Signature signature = joinPoint.getSignature();
		if(signature == null) return null;
		Method method = ((MethodSignature) signature).getMethod();
		return method.getAnnotation(RecordLogger.class);
	}
	
	/**
	 * 获取方法参数(排除request,response)
	 * @param joinPoint
	 * @return
	 */
	private List<Object> getArgs(JoinPoint joinPoint) {
		List<Object> results = Lists.newArrayList();
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length > 0) {
			for (Object object : args) {
				if(object instanceof HttpServletRequest || object instanceof HttpServletResponse){
				} else {
					results.add(object);
				}
			}
		}
		return results;
	}
}
