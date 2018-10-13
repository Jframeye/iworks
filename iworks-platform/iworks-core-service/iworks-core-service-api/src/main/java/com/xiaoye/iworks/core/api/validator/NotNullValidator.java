package com.xiaoye.iworks.core.api.validator;

import com.xiaoye.iworks.core.api.validator.annotation.NotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义不为空校验器
 * @author yehl
 * @date 2018年6月30日
 */
public class NotNullValidator implements ConstraintValidator<NotNull, Object> {

	@Override
	public void initialize(NotNull constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value instanceof String) {
			String val = (String) value;
			return val != null && val.trim().length() > 0;
		} else {
			return value != null;
		}
	}
}
