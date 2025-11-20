package com.example.up_final.domain.contest.service;

import com.example.up_final.domain.contest.domain.Contest;
import com.example.up_final.domain.contest.presentation.dto.req.ContestCreateReq;
import com.example.up_final.domain.contest.presentation.dto.res.ContestRes;
import com.example.up_final.domain.member.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface ContestMapper {

    @Mapping(target = "basicInfo", expression = "java(toBasicInfo(contest))")
    @Mapping(target = "registrationInfo", expression = "java(toRegistrationInfo(contest))")
    @Mapping(target = "additionalInfo", expression = "java(toAdditionalInfo(contest))")
    ContestRes toContestRes(Contest contest);

    List<ContestRes> toContestResList(List<Contest> contests);

    default ContestRes.BasicInfo toBasicInfo(Contest contest) {
        return new ContestRes.BasicInfo(
                contest.getTitle(),
                contest.getHost(),
                contest.getRegion(),
                contest.getTarget(),
                contest.getCategory()
        );
    }

    default ContestRes.RegistrationInfo toRegistrationInfo(Contest contest) {
        return new ContestRes.RegistrationInfo(
                contest.getRegistration_start(),
                contest.getRegistration_end(),
                contest.getRegistration_way(),
                contest.getRegistration_link(),
                contest.getParticipation_fee()
        );
    }

    default ContestRes.AdditionalInfo toAdditionalInfo(Contest contest) {
        return new ContestRes.AdditionalInfo(
                contest.getAward(),
                contest.getHome_page(),
                contest.getContact(),
                contest.getEtc(),
                contest.getImage_path(),
                contest.getLike_num(),
                contest.getCreated_at()
        );
    }

    default Contest toContest(ContestCreateReq req, Member writer) {
        return Contest.builder()
                // BasicInfo
                .title(req.basicInfo().title())
                .host(req.basicInfo().host())
                .region(req.basicInfo().region())
                .target(req.basicInfo().target())
                .category(req.basicInfo().category())
                // RegistrationInfo
                .registration_start(req.registrationInfo().registration_start())
                .registration_end(req.registrationInfo().registration_end())
                .registration_way(req.registrationInfo().registration_way())
                .registration_link(req.registrationInfo().registration_link())
                .participation_fee(req.registrationInfo().participation_fee())
                // AdditionalInfo
                .award(req.additionalInfo().award())
                .home_page(req.additionalInfo().home_page())
                .contact(req.additionalInfo().contact())
                .etc(req.additionalInfo().etc())
                .image_path(req.additionalInfo().image_path())
                // 공통/기본값
                .like_num(0)
                .created_at(LocalDateTime.now())
                .writer(writer)
                .build();
    }

    default void updateContest(Contest contest, ContestCreateReq req) {
        contest.updateBasicInfo(req.basicInfo());
        contest.updateRegistrationInfo(req.registrationInfo());
        contest.updateAdditionalInfo(req.additionalInfo());
    }
}