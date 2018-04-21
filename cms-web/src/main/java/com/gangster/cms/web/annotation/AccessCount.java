package com.gangster.cms.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 添加此注解以进行统计,value为统计类型，对应数据库中的count_type.
 * 注意：被注解的方法中须有参数被{@link CountParam}注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessCount {
    CountType value();
}
