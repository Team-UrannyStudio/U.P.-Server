package com.temp.up_v3.jwt.dto;

import com.temp.up_v3.jwt.Authority;
import com.temp.up_v3.jwt.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@Builder
public class MemberRequestDto {
    private String uid;
    private String name;
    private String password;
    private String address;
    private String phone;
    private String email;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .uid(uid)
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .address(address)
                .phone(phone)
                .email(email)
                .build();
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(uid, password);
    }
}
