package com.example.up_final.domain.member.presentation.dto.req;

import com.example.up_final.domain.member.domain.Member;
import com.example.up_final.domain.member.domain.enums.Authority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public record MemberCreateReq(
        String uid,
        String name,
        String password,
        String address,
        String phone,
        String email,
        String profile
) {
    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .uid(uid)
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .address(address)
                .phone(phone)
                .email(email)
                .profile(profile)
                .build();
    }
}
