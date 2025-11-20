package com.example.up_final.global.jwt.presentation.dto.req;

public record TokenReq(
        String accessToken,
        String refreshToken
) {
}
