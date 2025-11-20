package com.example.up_final.domain.contest.error;

import com.example.up_final.global.common.exception.error.CustomErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ContestErrorCode implements CustomErrorCode {
    CONTEST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 대회 게시물을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}

