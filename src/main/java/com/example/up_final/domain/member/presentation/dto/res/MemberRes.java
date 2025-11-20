package com.example.up_final.domain.member.presentation.dto.res;

import com.example.up_final.domain.member.domain.Member;

public record MemberRes(
        String uid,
        String name,
        String password,
        String address,
        String phone,
        String email,
        String profile
) {
    public static MemberRes from(Member member) {
        return new MemberRes(
                member.getUid(),
                member.getName(),
                member.getPassword(),
                member.getAddress(),
                member.getPhone(),
                member.getEmail(),
                member.getProfile()
        );
    }
}
