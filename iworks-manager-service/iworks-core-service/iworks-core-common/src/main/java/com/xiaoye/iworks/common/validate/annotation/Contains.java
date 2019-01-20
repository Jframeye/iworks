package com.xiaoye.iworks.common.validate.annotation;

import com.xiaoye.iworks.common.validate.ContainsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 包含校验器注解
 * @author yehl
 * @date 2018年6月30日
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContainsValidator.class)
public @interface Contains {

	String field() default "";
	
	String message() default "value not contains";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
