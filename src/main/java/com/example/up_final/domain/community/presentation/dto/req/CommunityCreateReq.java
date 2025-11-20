package com.example.up_final.domain.community.presentation.dto.req;

import com.example.up_final.domain.community.domain.Community;
import com.example.up_final.domain.community.domain.enums.CommunityCategory;
import com.example.up_final.domain.member.domain.Member;

import java.time.LocalDateTime;

public record CommunityCreateReq(
        String title,
        CommunityCategory category,
        String content,
        String image_path
) {
    public static Community toCommunity(CommunityCreateReq req, Member user) {
        return Community.builder()
                .title(req.title())
                .category(req.category())
                .content(req.content())
                .like_num(0)
                .comment_num(0)
                .image_path(req.image_path)
                .created_at(LocalDateTime.now())
                .writer(user)
                .build();
    }
}
