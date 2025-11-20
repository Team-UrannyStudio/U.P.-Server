package com.example.up_final.domain.comment.presentation.dto.req;

import com.example.up_final.domain.comment.domain.Comment;
import com.example.up_final.domain.comment.domain.enums.ContentType;
import com.example.up_final.domain.member.domain.Member;

import java.time.LocalDateTime;

public record CommentCreateReq(
        String content,
        Long parent_id,
        ContentType parent_type
) {
    public static Comment toComment(CommentCreateReq req, Member writer) {
        return Comment.builder()
                .writer(writer)
                .parentId(req.parent_id())
                .parentType(req.parent_type)
                .created_at(LocalDateTime.now())
                .content(req.content())
                .like_num(0)
                .dislike_num(0)
                .build();
    }
}
