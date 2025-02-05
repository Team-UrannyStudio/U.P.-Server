package com.temp.up_v3.service;

import com.temp.up_v3.domain.Contest;
import com.temp.up_v3.domain.ImageDetails;
import com.temp.up_v3.domain.Member;
import com.temp.up_v3.dto.contest.ContestListResponseDto;
import com.temp.up_v3.dto.contest.ContestRequestDto;
import com.temp.up_v3.dto.contest.ContestResponseDto;
import com.temp.up_v3.dto.others.ImageInfo;
import com.temp.up_v3.repository.ContestRepository;
import com.temp.up_v3.repository.ImageRepository;
import com.temp.up_v3.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hibernate.type.descriptor.java.CoercionHelper.toLong;

@Service
@AllArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    //글 생성
    public Contest createContest(ContestRequestDto requestDto, MultipartFile image) {

        Contest contest = new Contest(requestDto);

        if (contest.getContact() == null) {
            contest.setContact(memberRepository.findByUid(SecurityContextHolder.getContext().getAuthentication().getName()).get().getEmail());
        }

        contest.setMember(memberRepository.findMemberByUid(SecurityContextHolder.getContext().getAuthentication().getName()));
        contestRepository.save(contest);

        ImageInfo imageInfo = imageService.uploadImage(image);

        ImageDetails imageDetails = new ImageDetails(imageInfo.getImageUrl(), imageInfo.getImageName());

        imageDetails.setContentId("contest_" + contest.getId());
        imageRepository.save(imageDetails);

        return contest;
    }

    //전부 찾기
    public List<ContestListResponseDto> findAllContests() {

        List<Contest> contestList = contestRepository.findAll();
        List<ContestListResponseDto> responseDtoList = new ArrayList<>();

        Collections.reverse(contestList);

        Integer count = 0;

        for (Contest contest : contestList) {

            ContestListResponseDto responseDto = new ContestListResponseDto(contest);
            responseDto.setImagePath(imageRepository.findByContentId("contest_" + contest.getId()).getImagePath());

            responseDtoList.add(responseDto);

            count++;
            if (count > 15) {
                break;
            }

        }

        return responseDtoList;
    }

    //한 개 찾기
    public ContestResponseDto findOneContest(Long id) {

        Contest contest = contestRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );

        ContestResponseDto responseDto = new ContestResponseDto(contest);
        responseDto.setImagePath(imageRepository.findByContentId("contest_" + id).getImagePath());

        return responseDto;
    }

    //글 수정
    @Transactional
    public void updateContest(Long id, ContestRequestDto requestDto, MultipartFile image) {

        ImageDetails imageDetails = imageRepository.findByContentId("contest_" + id);

        imageService.deleteImage(imageDetails.getImageName());

        ImageInfo imageInfo = imageService.uploadImage(image);

        imageDetails.update(imageInfo.getImageUrl(), imageInfo.getImageName());

        Contest contest = contestRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        contest.update(requestDto);
    }

    //글 삭제
    public void deleteContest(Long id) {

        contestRepository.deleteById(id);

        imageService.deleteImage(imageRepository.findByContentId("contest_" + id).getImageName());

        imageRepository.delete(imageRepository.findByContentId("contest_" + id));
    }

    @Transactional
    public void likeContest(Long contestId) {

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findByUid(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new IllegalArgumentException("해당 콘테스트가 존재하지 않습니다."));

        if (member.getLiked().contains("contest_" + contestId)) {

            member.getLiked().remove("contest_" + contestId);
            contest.setLike_num(contest.getLike_num() - 1);

        } else {

            member.getLiked().add("contest_" + contestId);
            contest.setLike_num(contest.getLike_num() + 1);

        }
    }
}
