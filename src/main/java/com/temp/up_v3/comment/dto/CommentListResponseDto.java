package com.temp.up_v3.comment.dto;

import com.temp.up_v3.comment.Comment;
import com.temp.up_v3.jwt.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentListResponseDto {
    private Long id;
    private Member member;
    private LocalDateTime created_at;
    private String mainContent;
    private Long like_num;
    private Long dislike_num;

    public CommentListResponseDto(Comment comment) {
        this.id = comment.getId();
        this.member = comment.getMember();
        this.created_at = comment.getCreated_at();
        this.mainContent = comment.getMainContent();
        this.like_num = comment.getLike_num();
        this.dislike_num = comment.getDislike_num();

    }
}
