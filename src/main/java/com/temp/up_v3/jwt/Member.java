package com.temp.up_v3.jwt;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @Column(nullable = false)
    @Size(min = 5, max = 12)
    private String uid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Size(min = 2, max = 13)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column
    @Pattern(regexp = "^(http|https)://.*$", message = "주소는 유효한 URL 형식이어야 합니다.")
    private String address;

    @Column
    private String profilePicture;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> liked;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> disLiked;

    @Builder
    public Member(String uid, String password, Authority authority, String phone, String address, String name, String email) {
        this.uid = uid;
        this.password = password;
        this.authority = authority;
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.email = email;
        this.liked = new ArrayList<>();
    }
}