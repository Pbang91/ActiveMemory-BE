package com.example.activememory.global.exception.annotation;

import com.example.activememory.global.exception.ExceptionCode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionData {
    ExceptionCode code();
    String details() default "";
}
