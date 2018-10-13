package com.xiaoye.iworks.core.api.validator.annotation;

import com.xiaoye.iworks.core.api.validator.SizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TODO
 * @author yehl
 * @date 2018年6月30日
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { SizeValidator.class })
public @interface Size {

	String field() default "";
	
	String message() default "{com.xiaoye.microservices.basic.validator.annotation.Size.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	int min() default 0;

	int max() default Integer.MAX_VALUE;
}
