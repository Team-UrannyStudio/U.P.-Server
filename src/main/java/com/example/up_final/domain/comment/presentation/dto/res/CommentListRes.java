package com.example.up_final.domain.comment.presentation.dto.res;

import com.example.up_final.domain.comment.domain.Comment;
import com.example.up_final.domain.member.domain.Member;

import java.time.LocalDateTime;

public record CommentListRes(
        Long id,
        Member writer,
        LocalDateTime created_at,
        String content,
        int like_num,
        int dislike_num
) {
    public static CommentListRes from(Comment comment) {
        return new CommentListRes(
                comment.getId(),
                comment.getWriter(),
                comment.getCreated_at(),
                comment.getContent(),
                comment.getLike_num(),
                comment.getDislike_num()
        );
    }
}
