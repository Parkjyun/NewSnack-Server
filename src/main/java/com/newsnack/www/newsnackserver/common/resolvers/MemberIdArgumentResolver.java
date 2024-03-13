package com.newsnack.www.newsnackserver.common.resolvers;

import com.newsnack.www.newsnackserver.annotation.MemberId;
import com.newsnack.www.newsnackserver.common.code.failure.MemberFailureCode;
import com.newsnack.www.newsnackserver.common.exception.MemberException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

@Component
public class MemberIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Long.class)
                && parameter.hasParameterAnnotation(MemberId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        MemberId memberId = parameter.getParameterAnnotation(MemberId.class);
        boolean forSecuredApi = memberId.isForSecuredApi();

        final Principal principal = webRequest.getUserPrincipal();
        if (principal == null) {
            if (forSecuredApi) {
                throw new MemberException(MemberFailureCode.MEMBER_NOT_FOUND);
            }
            return null;
        }
        return Long.valueOf(principal.getName());
    }
}
