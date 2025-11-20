package com.example.up_final.domain.comment.domain;

import com.example.up_final.domain.comment.domain.enums.ContentType;
import com.example.up_final.domain.comment.presentation.dto.req.CommentUpdateReq;
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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member writer;

    @Column
    private Long parentId;

    @Column
    private ContentType parentType;

    @Column
    private LocalDateTime created_at;

    @Column
    private String content;

    @Column
    private int like_num;

    @Column
    private int dislike_num;

    public void update(CommentUpdateReq req) {
        this.content = req.content();
    }

    public void updateLike_num(int like_num) {
        this.like_num = like_num;
    }

    public void updateDislike_num(int dislike_num) {
        this.dislike_num = dislike_num;
    }

}
