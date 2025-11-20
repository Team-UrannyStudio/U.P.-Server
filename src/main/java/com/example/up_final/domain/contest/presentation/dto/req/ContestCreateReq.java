package com.example.up_final.domain.contest.presentation.dto.req;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.contest.domain.enums.Region;
import com.example.up_final.domain.contest.domain.enums.Target;

import java.time.LocalDate;

public record ContestCreateReq(
        BasicInfo basicInfo,
        RegistrationInfo registrationInfo,
        AdditionalInfo additionalInfo
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
            String image_path
    ) {}
}