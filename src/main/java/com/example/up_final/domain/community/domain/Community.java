package com.example.up_final.domain.community.domain;


import com.example.up_final.domain.community.domain.enums.CommunityCategory;
import com.example.up_final.domain.community.presentation.dto.req.CommunityCreateReq;
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
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Enumerated(EnumType.STRING)
    @Column
    private CommunityCategory category;

    @Column
    private String content;

    @Column
    private int like_num;

    @Column
    private int comment_num;

    @Column
    private String image_path;

    @Column
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private Member writer;

    public void update(CommunityCreateReq req) {
        this.title = req.title();
        this.category = req.category();
        this.content = req.content();
        this.image_path = req.image_path();
    }

    public void updateLike_num(int like_num) {
        this.like_num = like_num;
    }
}
