package com.xiaoye.iworks.common.validate;

import com.xiaoye.iworks.common.validate.annotation.Contains;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 枚举校验器
 * @author yehl
 * @date 2018年6月30日
 */
public class ContainsValidator implements ConstraintValidator<Contains, Object> {

	@Override
	public void initialize(Contains constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		System.out.println(value.toString());
		return false;
	}
}
