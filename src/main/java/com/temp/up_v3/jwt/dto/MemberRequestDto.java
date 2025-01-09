package com.temp.up_v3.jwt.dto;

import com.temp.up_v3.domain.Authority;
import com.temp.up_v3.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@AllArgsConstructor
@Builder
public class MemberRequestDto {
    private String uid;
    private String name;
    private String password;
    private String address;
    private String phone;

    public Member toMember(org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        return Member.builder()
                .uid(uid)
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .address(address)
                .phone(phone)
                .build();
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(uid, password);
    }
}
