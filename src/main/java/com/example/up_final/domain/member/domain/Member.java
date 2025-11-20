package com.example.up_final.domain.member.domain;

import com.example.up_final.domain.member.domain.enums.Authority;
import com.example.up_final.domain.member.presentation.dto.req.MemberUpdateReq;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder
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
    private String address;

    @Column
    private String profile;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> likedComment;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> dislikedComment;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> likedContest;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> likedCommunity;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> likedTeam;

    public void update(MemberUpdateReq req) {
        this.uid = req.uid();
        this.name = req.name();
        this.password = req.password();
        this.phone = req.phone();
        this.address = req.address();
        this.profile = req.profile();
        this.email = req.email();
    }
}