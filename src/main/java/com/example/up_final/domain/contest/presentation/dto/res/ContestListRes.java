package com.example.up_final.domain.contest.presentation.dto.res;

import com.example.up_final.domain.contest.domain.Contest;
import com.example.up_final.domain.contest.domain.enums.ContestCategory;

import java.time.LocalDate;

public record ContestListRes(
        Long id,
        String title,
        ContestCategory category,
        LocalDate registration_start,
        LocalDate registration_end,
        String host,
        int like_num,
        String image_path
) {
    public static ContestListRes from(Contest contest) {
        return new ContestListRes(
                contest.getId(),
                contest.getTitle(),
                contest.getCategory(),
                contest.getRegistration_start(),
                contest.getRegistration_end(),
                contest.getHost(),
                contest.getLike_num(),
                contest.getImage_path()
        );
    }
}
