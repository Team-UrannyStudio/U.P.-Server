package com.example.up_final.global.jwt.auth;

import com.example.up_final.global.common.exception.CustomException;
import com.example.up_final.domain.member.domain.Member;
import com.example.up_final.domain.member.error.AuthErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSessionHolder {

    public Member getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("인증되지 않은 요청 - Authentication is null or not authenticated");
            throw new CustomException(AuthErrorCode.USER_NOT_FOUND);
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof String && "anonymousUser".equals(principal)) {
            log.warn("익명 사용자 요청");
            throw new CustomException(AuthErrorCode.USER_NOT_FOUND);
        }

        if (principal instanceof AuthDetails authDetails) {
            return authDetails.getMember();
        } else {
            log.error("예상하지 못한 Principal 타입: {}", principal.getClass().getName());
            throw new CustomException(AuthErrorCode.USER_NOT_FOUND);
        }
    }
}