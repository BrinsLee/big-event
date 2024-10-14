package com.brins.anno;

import com.brins.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Created by lipeilin on 2024/10/14.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
// 指定提供校验规则的类
@Constraint(validatedBy = {StateValidation.class})
public @interface State {

    // 提供校验失败后的提示信息
    String message() default "State参数的值只能是已发布|草稿";

    // 指定分组
    Class<?>[] groups() default { };

    // 负载，获取State注解的附加信息
    Class<? extends Payload>[] payload() default { };
}
