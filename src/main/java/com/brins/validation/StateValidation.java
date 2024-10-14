package com.brins.validation;

import com.brins.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

/**
 * Created by lipeilin on 2024/10/14.
 * 泛型参数 <A extends Annotation, T>
 * A 指定为哪个注解提供校验
 * T 校验的数据类型
 */
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public void initialize(State constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    /**
     * 提供校验规则
     * @param value 将来要校验的数据
     * @param context
     * @return false：校验不通过 ; true: 校验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.equals("已发布") || value.equals("草稿");
    }






}
