package com.xiaoye.iworks.core.api.validator.annotation;

import com.xiaoye.iworks.core.api.validator.NotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义空检验器注解
 * @author yehl
 * @date 2018年6月30日
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullValidator.class)
public @interface NotNull {

	String field() default "";
	
	String message() default "com.xiaoye.microservices.basic.validator.annotation.NotNull.message";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
