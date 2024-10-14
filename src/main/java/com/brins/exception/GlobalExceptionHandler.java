package com.brins.exception;

import com.brins.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by lipeilin on 2024/1/21.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage(): "操作失败");
    }
}
