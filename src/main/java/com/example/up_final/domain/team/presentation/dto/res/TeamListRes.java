package com.example.up_final.domain.team.presentation.dto.res;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.team.domain.Team;
import com.example.up_final.domain.member.domain.Member;

public record TeamListRes(
        Long id,
        String title,
        String work,
        ContestCategory category,
        int like_num,
        String image_path,
        Member writer
) {
    public static TeamListRes from(Team team) {
        return new TeamListRes(
                team.getId(),
                team.getTitle(),
                team.getWork(),
                team.getCategory(),
                team.getLike_num(),
                team.getImage_path(),
                team.getWriter()
        );
    }
}
