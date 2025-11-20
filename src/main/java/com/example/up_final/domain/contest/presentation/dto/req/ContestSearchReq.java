package com.example.up_final.domain.contest.presentation.dto.req;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.contest.domain.enums.Region;
import com.example.up_final.domain.contest.domain.enums.Target;

public record ContestSearchReq(
        ContestCategory category,
        Target target,
        String timeFilter,
        Region region,
        String keyword
) {}