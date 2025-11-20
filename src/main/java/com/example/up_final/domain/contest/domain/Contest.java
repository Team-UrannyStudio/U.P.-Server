package com.example.up_final.domain.contest.domain;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.contest.domain.enums.Region;
import com.example.up_final.domain.contest.domain.enums.Target;
import com.example.up_final.domain.contest.presentation.dto.req.ContestCreateReq;
import com.example.up_final.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String host;

    @Enumerated(EnumType.STRING)
    @Column
    private Region region;

    @Column
    private LocalDate registration_start;

    @Column
    private LocalDate registration_end;

    @Column
    private String registration_way;

    @Column
    private String registration_link;

    @Column
    private int participation_fee;

    @Column
    private String award;

    @Column
    private String home_page;

    @Enumerated(EnumType.STRING)
    @Column
    private Target target;

    @Enumerated(EnumType.STRING)
    @Column
    private ContestCategory category;

    @Column
    private String contact;

    @Column
    private String etc;

    @Column
    private int like_num;

    @Column
    private String image_path;

    @Column
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private Member writer;

    public void updateBasicInfo(ContestCreateReq.BasicInfo basicInfo) {
        this.title = basicInfo.title();
        this.host = basicInfo.host();
        this.region = basicInfo.region();
        this.target = basicInfo.target();
        this.category = basicInfo.category();
    }

    public void updateRegistrationInfo(ContestCreateReq.RegistrationInfo reg) {
        this.registration_start = reg.registration_start();
        this.registration_end = reg.registration_end();
        this.registration_way = reg.registration_way();
        this.registration_link = reg.registration_link();
        this.participation_fee = reg.participation_fee();
    }

    public void updateAdditionalInfo(ContestCreateReq.AdditionalInfo info) {
        this.award = info.award();
        this.home_page = info.home_page();
        this.contact = info.contact();
        this.etc = info.etc();
        this.image_path = info.image_path();
    }

    public void updateLike_num(int like_num) {
        this.like_num = like_num;
    }
}
