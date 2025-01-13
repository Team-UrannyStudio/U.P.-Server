package com.temp.up_v3.service;

import com.temp.up_v3.domain.Contest;
import com.temp.up_v3.domain.Member;
import com.temp.up_v3.dto.contest.ContestListResponseDto;
import com.temp.up_v3.dto.contest.ContestRequestDto;
import com.temp.up_v3.dto.contest.ContestResponseDto;
import com.temp.up_v3.repository.ContestRepository;
import com.temp.up_v3.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.type.descriptor.java.CoercionHelper.toLong;

@Service
@AllArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final MemberRepository memberRepository;
    private final ImageService imageService;

    public Contest createContest(ContestRequestDto requestDto, MultipartFile image) {
        Contest contest = new Contest(requestDto);
        if (contest.getContact() == null) {
            contest.setContact(memberRepository.findByUid(SecurityContextHolder.getContext().getAuthentication().getName()).get().getEmail());
        }
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        contest.setMember(memberRepository.findMemberByUid(SecurityContextHolder.getContext().getAuthentication().getName()));
        imageService.uploadImage(contest, image);
        contestRepository.save(contest);
        return contest;
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
