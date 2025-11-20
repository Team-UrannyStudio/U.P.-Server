package com.example.up_final.domain.team.presentation.dto.req;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.team.domain.Team;
import com.example.up_final.domain.member.domain.Member;

import java.time.LocalDateTime;

public record TeamCreateReq(
        String title,
        String work,
        String requirements,
        String preferred,
        String contact,
        ContestCategory category,
        String description,
        String image_path
) {
    public static Team toTeam(TeamCreateReq req, Member writer) {
        return Team.builder()
                .title(req.title())
                .work(req.work())
                .requirements(req.requirements())
                .preferred(req.preferred())
                .contact(req.contact())
                .category(req.category())
                .description(req.description())
                .like_num(0)
                .image_path(req.image_path())
                .created_at(LocalDateTime.now())
                .writer(writer)
                .build();
    }
}
