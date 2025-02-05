package com.temp.up_v3.domain;

import com.temp.up_v3.dto.help.HelpRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static org.hibernate.type.descriptor.java.CoercionHelper.toLong;

@Entity
@Data
@NoArgsConstructor
public class Help {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String working;

    @Column(nullable = false)
    private String limitPeople;

    @Column(nullable = false)
    private String advantage;

    @Column(nullable = false)
    private String contactLink;

    @Column(nullable = false)
    private String mainLetter;

    @Column
    private Long like_num;

    @Column
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    public Help(HelpRequestDto requestDto) {
        this.created_at = LocalDateTime.now();
        this.title = requestDto.getTitle();
        if (requestDto.getCategory() == null) {
            requestDto.setCategory("전체");
        }
        this.category = requestDto.getCategory();
        this.working = requestDto.getWorking();
        this.limitPeople = requestDto.getLimitPeople();
        this.advantage = requestDto.getAdvantage();
        this.like_num = toLong(0);
        this.contactLink = requestDto.getContactLink();
        this.mainLetter = requestDto.getMainLetter();
    }

    public void update(HelpRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.working = requestDto.getWorking();
        this.limitPeople = requestDto.getLimitPeople();
        this.advantage = requestDto.getAdvantage();
        this.contactLink = requestDto.getContactLink();
        this.mainLetter = requestDto.getMainLetter();
    }
}
