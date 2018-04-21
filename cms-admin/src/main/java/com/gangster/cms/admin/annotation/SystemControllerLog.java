package com.gangster.cms.admin.annotation;

/**
 * @author Yoke
 * Created on 2018/4/11
 */

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
    String description() default "";
}
