package com.example.up_final.domain.team.presentation.dto.req;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;

public record TeamSearchReq(
        ContestCategory category,
        String keyword
) {
}
