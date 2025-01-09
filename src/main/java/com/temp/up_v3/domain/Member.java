package com.temp.up_v3.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @Column(nullable = false)
    @Size(min = 5, max = 12)
    private String uid;

    @Column(nullable = false)
    @Size(min = 2, max = 13)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private String phone;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column
    @Pattern(regexp = "^(http|https)://.*$", message = "주소는 유효한 URL 형식이어야 합니다.")
    private String address;

    @Builder
    public Member(String uid, String password, Authority authority, String phone, String address, String name) {
        this.uid = uid;
        this.password = password;
        this.authority = authority;
        this.phone = phone;
        this.address = address;
        this.name = name;
    }
}