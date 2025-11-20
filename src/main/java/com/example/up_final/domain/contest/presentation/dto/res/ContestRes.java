package com.example.up_final.domain.contest.presentation.dto.res;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.contest.domain.enums.Region;
import com.example.up_final.domain.contest.domain.enums.Target;
import com.example.up_final.domain.member.domain.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContestRes(
        Long id,
        BasicInfo basicInfo,
        RegistrationInfo registrationInfo,
        AdditionalInfo additionalInfo,
        Member writer
) {
    public record BasicInfo(
            String title,
            String host,
            Region region,
            Target target,
            ContestCategory category
    ) {}

    public record RegistrationInfo(
            LocalDate registration_start,
            LocalDate registration_end,
            String registration_way,
            String registration_link,
            int participation_fee
    ) {}

    public record AdditionalInfo(
            String award,
            String home_page,
            String contact,
            String etc,
            String image_path,
            int like_num,
            LocalDateTime created_at
    ) {}
}
