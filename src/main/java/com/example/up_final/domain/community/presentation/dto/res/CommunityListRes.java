package com.example.up_final.domain.community.presentation.dto.res;

import com.example.up_final.domain.community.domain.Community;
import com.example.up_final.domain.community.domain.enums.CommunityCategory;
import com.example.up_final.domain.member.domain.Member;

import java.time.LocalDateTime;

public record CommunityListRes(
        Long id,
        String title,
        CommunityCategory category,
        Member writer,
        LocalDateTime created_at,
        int comment_num
) {
    public static CommunityListRes from(Community community) {
        return new CommunityListRes(
                community.getId(),
                community.getTitle(),
                community.getCategory(),
                community.getWriter(),
                community.getCreated_at(),
                community.getComment_num()
        );
    }
}
