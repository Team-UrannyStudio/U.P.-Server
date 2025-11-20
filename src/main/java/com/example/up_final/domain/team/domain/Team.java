package com.example.up_final.domain.team.domain;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.team.presentation.dto.req.TeamCreateReq;
import com.example.up_final.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String work;

    @Column
    private String requirements;

    @Column
    private String preferred;

    @Column
    private String contact;

    @Column
    private ContestCategory category;

    @Column
    private String description;

    @Column
    private int like_num;

    @Column
    private String image_path;

    @Column
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private Member writer;

    public void update(TeamCreateReq req) {
        this.title = req.title();
        this.work = req.work();
        this.requirements = req.requirements();
        this.preferred = req.preferred();
        this.contact = req.contact();
        this.category = req.category();
        this.description = req.description();
        this.image_path = req.image_path();
    }

    public void updateLike_num(int like_num) {
        this.like_num = like_num;
    }
}
