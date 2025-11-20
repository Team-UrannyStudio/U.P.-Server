package com.example.up_final.global.jwt.presentation.dto.res;

public record TokenRes(
        String grantType,
        String accessToken,
        Long accessTokenExpiresIn,
        String refreshToken
) {
    public static TokenRes from(
            String grantType,
            String accessToken,
            Long expiresIn,
            String refreshToken
    ) {
        return new TokenRes(
                grantType,
                accessToken,
                expiresIn,
                refreshToken
        );
    }
}
