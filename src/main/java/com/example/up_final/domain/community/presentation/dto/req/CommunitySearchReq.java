package com.example.up_final.domain.community.presentation.dto.req;

import com.example.up_final.domain.community.domain.enums.CommunityCategory;

public record CommunitySearchReq(
        CommunityCategory category,
        String keyword
) {}