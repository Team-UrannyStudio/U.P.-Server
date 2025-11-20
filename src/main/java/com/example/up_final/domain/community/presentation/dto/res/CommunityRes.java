package com.example.up_final.domain.community.presentation.dto.res;

import com.example.up_final.domain.community.domain.Community;
import com.example.up_final.domain.community.domain.enums.CommunityCategory;
import com.example.up_final.domain.member.domain.Member;

import java.time.LocalDateTime;

public record CommunityRes(
        Long id,
        String title,
        CommunityCategory category,
        String content,
        int like_num,
        String image_path,
        LocalDateTime created_at,
        Member writer
) {
    public static CommunityRes from(Community community) {
        return new CommunityRes(
                community.getId(),
                community.getTitle(),
                community.getCategory(),
                community.getContent(),
                community.getLike_num(),
                community.getImage_path(),
                community.getCreated_at(),
                community.getWriter()
        );
    }
}
