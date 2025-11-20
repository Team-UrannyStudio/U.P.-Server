package com.example.up_final.domain.member.presentation.dto.req;

public record MemberUpdateReq(
        String uid,
        String name,
        String password,
        String address,
        String phone,
        String email,
        String profile
) {
}
