package com.temp.up_v3.service;

import com.temp.up_v3.domain.Help;
import com.temp.up_v3.domain.ImageDetails;
import com.temp.up_v3.domain.Member;
import com.temp.up_v3.dto.help.HelpListResponseDto;
import com.temp.up_v3.dto.help.HelpRequestDto;
import com.temp.up_v3.dto.help.HelpResponseDto;
import com.temp.up_v3.dto.others.ImageInfo;
import com.temp.up_v3.repository.HelpRepository;
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
public class HelpService {

    private final HelpRepository helpRepository;
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    //글 생성
    public Help createHelp(HelpRequestDto requestDto, MultipartFile image) {

        Help help = new Help(requestDto);

        help.setMember(memberRepository.findMemberByUid(SecurityContextHolder.getContext().getAuthentication().getName()));
        helpRepository.save(help);

        ImageInfo imageInfo = imageService.uploadImage(image);

        ImageDetails imageDetails = new ImageDetails(imageInfo.getImageUrl(), imageInfo.getImageName());

        imageDetails.setContentId("help_" + help.getId());
        imageRepository.save(imageDetails);

        return help;
    }

    //전부 찾기
    public List<HelpListResponseDto> findAllHelps() {

        List<Help> helpList = helpRepository.findAll();
        List<HelpListResponseDto> responseDtoList = new ArrayList<>();

        Collections.reverse(helpList);

        Integer count = 0;

        for (Help help : helpList) {

            HelpListResponseDto responseDto = new HelpListResponseDto(help);
            responseDto.setImagePath(imageRepository.findByContentId("help_" + help.getId()).getImagePath());

            responseDtoList.add(responseDto);

            count++;
            if (count > 15) {
                break;
            }

        }

        return responseDtoList;
    }

    //한 개 찾기
    public HelpResponseDto findOneHelp(Long id) {

        Help help = helpRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );

        HelpResponseDto responseDto = new HelpResponseDto(help);
        responseDto.setImagePath(imageRepository.findByContentId("help_" + id).getImagePath());

        return responseDto;
    }

    //글 수정
    @Transactional
    public void updateHelp(Long id, HelpRequestDto requestDto, MultipartFile image) {

        ImageDetails imageDetails = imageRepository.findByContentId("help_" + id);

        imageService.deleteImage(imageDetails.getImageName());

        ImageInfo imageInfo = imageService.uploadImage(image);

        imageDetails.update(imageInfo.getImageUrl(), imageInfo.getImageName());

        Help help = helpRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        help.update(requestDto);
    }

    //글 삭제
    public void deleteHelp(Long id) {

        helpRepository.deleteById(id);

        imageService.deleteImage(imageRepository.findByContentId("help_" + id).getImageName());

        imageRepository.delete(imageRepository.findByContentId("help_" + id));
    }

    @Transactional
    public void likeHelp(Long helpId) {

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findByUid(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        Help help = helpRepository.findById(helpId)
                .orElseThrow(() -> new IllegalArgumentException("해당 콘테스트가 존재하지 않습니다."));

        if (member.getLiked().contains("help_" + helpId)) {

            member.getLiked().remove("help_" + helpId);
            help.setLike_num(help.getLike_num() - 1);

        } else {

            member.getLiked().add("help_" + helpId);
            help.setLike_num(help.getLike_num() + 1);
            help.setLike_num(help.getLike_num() + 1);

        }
    }
}
