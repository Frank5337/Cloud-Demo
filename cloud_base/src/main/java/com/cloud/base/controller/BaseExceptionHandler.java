package com.cloud.base.controller;

import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: zbl
 * @Date: 11:44 2019/10/2
 * @Description: 异常处理类
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        log.error(e.getMessage());
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
