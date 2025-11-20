package com.example.up_final.domain.team.presentation.dto.res;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.team.domain.Team;
import com.example.up_final.domain.member.domain.Member;

import java.time.LocalDateTime;

public record TeamRes(
        Long id,
        String title,
        String work,
        String requirements,
        String preferred,
        String contact,
        ContestCategory category,
        String description,
        int like_num,
        String image_path,
        LocalDateTime created_at,
        Member writer
) {
    public static TeamRes from(Team team) {
        return new TeamRes(
                team.getId(),
                team.getTitle(),
                team.getWork(),
                team.getRequirements(),
                team.getPreferred(),
                team.getContact(),
                team.getCategory(),
                team.getDescription(),
                team.getLike_num(),
                team.getImage_path(),
                team.getCreated_at(),
                team.getWriter()
        );
    }
}
