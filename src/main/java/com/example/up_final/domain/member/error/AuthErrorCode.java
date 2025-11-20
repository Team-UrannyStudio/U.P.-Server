package com.example.up_final.domain.member.error;

import com.example.up_final.global.common.exception.error.CustomErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements CustomErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾지 못하였습니다.");

    private final HttpStatus status;
    private final String message;
}