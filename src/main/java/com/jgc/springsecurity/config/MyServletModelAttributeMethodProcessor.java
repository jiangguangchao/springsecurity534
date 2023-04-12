package com.jgc.springsecurity.config;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2023-04-03 16:19
 */
public class MyServletModelAttributeMethodProcessor extends ServletModelAttributeMethodProcessor {

    public MyServletModelAttributeMethodProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }



}
