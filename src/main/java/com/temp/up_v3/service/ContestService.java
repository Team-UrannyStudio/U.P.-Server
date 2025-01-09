package com.temp.up_v3.service;

import com.temp.up_v3.domain.Contest;
import com.temp.up_v3.domain.Member;
import com.temp.up_v3.dto.contest.ContestListResponseDto;
import com.temp.up_v3.dto.contest.ContestRequestDto;
import com.temp.up_v3.dto.contest.ContestResponseDto;
import com.temp.up_v3.repository.ContestRepository;
import com.temp.up_v3.repository.MemberRepository;
import com.temp.up_v3.util.SecurityUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final MemberRepository memberRepository;

    public ContestResponseDto createContest(ContestRequestDto requestDto) {

        Contest contest = new Contest(requestDto);
        contest.setMember(memberRepository.findMemberByUid(SecurityContextHolder.getContext().getAuthentication().getName()));
        contestRepository.save(contest);
        return new ContestResponseDto(contest);
    }

    public List<ContestListResponseDto> findAllContests() {
        List<Contest> contestList = contestRepository.findAll();
        List<ContestListResponseDto> responseDtoList = new ArrayList<>();
        for (Contest contest : contestList) {
            responseDtoList.add(
                    new ContestListResponseDto(contest)
            );
        }
        Collections.reverse(responseDtoList);
        return responseDtoList;
    }

    public ContestResponseDto findOneContest(Long id) {
        Contest contest = contestRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );
        return new ContestResponseDto(contest);
    }

    //글 수정
    @Transactional
    public void updateContest(Long id, ContestRequestDto requestDto) {
        Contest contest = contestRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        contest.update(requestDto);
    }

    @Transactional
    public void deleteContest(Long id) {
        contestRepository.deleteById(id);
    }
}
