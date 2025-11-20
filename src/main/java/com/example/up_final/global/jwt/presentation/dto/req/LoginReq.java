package com.example.up_final.global.jwt.presentation.dto.req;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginReq(
        String uid,
        String password
) {
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(uid, password);
    }
}
