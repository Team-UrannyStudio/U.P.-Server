package com.temp.up_v3.domain;

import com.temp.up_v3.dto.contest.ContestRequestDto;
import com.temp.up_v3.jwt.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

import static org.hibernate.type.descriptor.java.CoercionHelper.toLong;

@Entity
@Data
@NoArgsConstructor
public class Contest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String category;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private String participants;

    @Column(nullable = false)
    private String start;

    @Column(nullable = false)
    private String finish;

    @Column(nullable = false)
    private String apply;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String prize;

    @Column(nullable = false)
    private String homepage;

    @Column(nullable = false)
    private String applyLink;

    @Column(nullable = false)
    private String contact;

    @Column
    private String etc;

    @Column
    private Long like_num;

    @Column
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public Contest(ContestRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.host = requestDto.getHost();
        this.participants = requestDto.getParticipants();
        this.start = requestDto.getStart();
        this.finish = requestDto.getFinish();
        this.apply = requestDto.getApply();
        this.price = requestDto.getPrice();
        this.location = requestDto.getLocation();
        this.prize = requestDto.getPrize();
        this.homepage = requestDto.getHomepage();
        this.applyLink = requestDto.getApplyLink();
        this.contact = requestDto.getContact();
        this.etc = requestDto.getEtc();
        this.like_num = toLong(0);
        this.created_at = LocalDateTime.now();
        if (requestDto.getCategory() == null) {
            requestDto.setCategory("전체");
        }
        this.category = requestDto.getCategory();
    }

    public void update(ContestRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.host = requestDto.getHost();
        this.participants = requestDto.getParticipants();
        this.start = requestDto.getStart();
        this.finish = requestDto.getFinish();
        this.apply = requestDto.getApply();
        this.price = requestDto.getPrice();
        this.location = requestDto.getLocation();
        this.prize = requestDto.getPrize();
        this.homepage = requestDto.getHomepage();
        this.applyLink = requestDto.getApplyLink();
        this.contact = requestDto.getContact();
        this.etc = requestDto.getEtc();
    }
}
