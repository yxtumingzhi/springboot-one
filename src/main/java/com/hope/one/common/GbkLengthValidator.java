//package com.hope.one.common;
//
//
///**
// * @author tumingzhi
// */
//public class /**/GbkLengthValidator implements ConstraintValidator<GbkLength, String> {
//
//	public GbkLength gbkLength;
//
//	@Override
//	public void initialize(GbkLength gbkLength) {
//		this.gbkLength = gbkLength;
//	}
//
//
//	@Override
//	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//		if (gbkLength.min() <= Func.lengthGbk(value) && Func.lengthGbk(value) <= gbkLength.max()) {
//			return true;
//		}
//		return false;
//	}
//}
