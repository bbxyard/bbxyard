package com.bbxyard.sfb.aspect.anno;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyHook {

    String value() default "";

}
