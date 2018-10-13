package com.xiaoye.iworks.core.api.validator;

import com.xiaoye.iworks.core.api.validator.annotation.Size;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TODO
 * @author yehl
 * @date 2018年6月30日
 */
public class SizeValidator implements ConstraintValidator<Size, Object> {

	@Override
	public void initialize(Size constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null) return false;
		if(value.getClass().isArray()) {
		}
		return false;
	}
}
