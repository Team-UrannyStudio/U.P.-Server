package com.temp.up_v3.controller;

import com.temp.up_v3.domain.Community;
import com.temp.up_v3.dto.community.CommunityListResponseDto;
import com.temp.up_v3.dto.community.CommunityRequestDto;
import com.temp.up_v3.dto.community.CommunityResponseDto;
import com.temp.up_v3.dto.others.IdDto;
import com.temp.up_v3.service.CommunityService;
import com.temp.up_v3.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/community")
@AllArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final ImageService imageService;


    // 글 등록
    @PostMapping("/post")
    public ResponseEntity<String> createCommunity(@RequestPart CommunityRequestDto requestDto, @RequestParam MultipartFile image){
        Community community = communityService.createCommunity(requestDto, image);
        return ResponseEntity.ok("upload success");
    }

    // 전체 목록 조회
    @GetMapping("/communitylist")
    public List<CommunityListResponseDto> getAllCommunities() {
        return communityService.findAllCommunities();
    }

    // 글 수정 시 특정 글 정보 반환
    @GetMapping("/update")
    public CommunityResponseDto getOneCommunity(@RequestBody Long id) {
        return communityService.findOneCommunity(id);
    }

    // 글 수정
    @PutMapping("/update")
    public ResponseEntity<String> updateCommunity(@RequestPart IdDto idDto, @RequestPart CommunityRequestDto requestDto, @RequestParam MultipartFile image) {
        communityService.updateCommunity(idDto.getId(), requestDto, image);
        return ResponseEntity.ok("success");
    }

    // 글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCommunity(@RequestBody IdDto idDto) {
        communityService.deleteCommunity(idDto.getId());
        return ResponseEntity.ok("success");
    }

    @PutMapping("/liked")
    public ResponseEntity<String> likedCommunity(@RequestBody IdDto idDto) {
        communityService.likeCommunity(idDto.getId());
        return ResponseEntity.ok("success");
    }
}