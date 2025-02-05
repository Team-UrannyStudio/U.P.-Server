package com.temp.up_v3.service;

import com.temp.up_v3.domain.Community;
import com.temp.up_v3.domain.ImageDetails;
import com.temp.up_v3.domain.Member;
import com.temp.up_v3.dto.community.CommunityListResponseDto;
import com.temp.up_v3.dto.community.CommunityRequestDto;
import com.temp.up_v3.dto.community.CommunityResponseDto;
import com.temp.up_v3.dto.others.ImageInfo;
import com.temp.up_v3.repository.CommunityRepository;
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

@Service
@AllArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    //글 생성
    public Community createCommunity(CommunityRequestDto requestDto, MultipartFile image) {

        Community community = new Community(requestDto);

        community.setMember(memberRepository.findMemberByUid(SecurityContextHolder.getContext().getAuthentication().getName()));
        communityRepository.save(community);

        ImageInfo imageInfo = imageService.uploadImage(image);

        ImageDetails imageDetails = new ImageDetails(imageInfo.getImageUrl(), imageInfo.getImageName());

        imageDetails.setContentId("community_" + community.getId());
        imageRepository.save(imageDetails);

        return community;
    }

    //전부 찾기
    public List<CommunityListResponseDto> findAllCommunities() {

        List<Community> communityList = communityRepository.findAll();
        List<CommunityListResponseDto> responseDtoList = new ArrayList<>();

        Collections.reverse(communityList);

        Integer count = 0;

        for (Community community : communityList) {

            CommunityListResponseDto responseDto = new CommunityListResponseDto(community);

            responseDtoList.add(responseDto);

            count++;
            if (count > 30) {
                break;
            }

        }

        return responseDtoList;
    }

    //한 개 찾기
    public CommunityResponseDto findOneCommunity(Long id) {

        Community community = communityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );

        CommunityResponseDto responseDto = new CommunityResponseDto(community);
        responseDto.setImagePath(imageRepository.findByContentId("community_" + id).getImagePath());

        return responseDto;
    }

    //글 수정
    @Transactional
    public void updateCommunity(Long id, CommunityRequestDto requestDto, MultipartFile image) {

        ImageDetails imageDetails = imageRepository.findByContentId("community_" + id);

        imageService.deleteImage(imageDetails.getImageName());

        ImageInfo imageInfo = imageService.uploadImage(image);

        imageDetails.update(imageInfo.getImageUrl(), imageInfo.getImageName());

        Community community = communityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        community.update(requestDto);
    }

    //글 삭제
    public void deleteCommunity(Long id) {

        communityRepository.deleteById(id);

        imageService.deleteImage(imageRepository.findByContentId("community_" + id).getImageName());

        imageRepository.delete(imageRepository.findByContentId("community_" + id));
    }

    @Transactional
    public void likeCommunity(Long communityId) {

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findByUid(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        if (member.getLiked().contains("community_" + communityId)) {

            member.getLiked().remove("community_" + communityId);

        } else {

            member.getLiked().add("community_" + communityId);

        }
    }
}
