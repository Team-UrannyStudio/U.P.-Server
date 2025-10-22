package com.example.up_final.jwt;

import com.example.up_final.jwt.dto.MemberUpdateRequestDto;
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
    private List<Long> likedComment;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> likedContent;

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
        this.profilePicture = "https://uranny.s3.ap-northeast-2.amazonaws.com/basic+profile+image.png";
        this.likedContent = new ArrayList<>();
        this.likedComment = new ArrayList<>();
        this.disLiked = new ArrayList<>();
    }

    public void update(MemberUpdateRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.name = requestDto.getName();
        this.phone = requestDto.getPhone();
        this.address = requestDto.getAddress();
    }
}