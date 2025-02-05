package com.temp.up_v3.controller;

import com.temp.up_v3.domain.Contest;
import com.temp.up_v3.dto.others.IdDto;
import com.temp.up_v3.dto.contest.ContestListResponseDto;
import com.temp.up_v3.dto.contest.ContestRequestDto;
import com.temp.up_v3.dto.contest.ContestResponseDto;
import com.temp.up_v3.service.ContestService;
import com.temp.up_v3.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/contest")
@AllArgsConstructor
public class ContestController {

    private final ContestService contestService;
    private final ImageService imageService;


    // 글 등록
    @PostMapping("/post")
    public ResponseEntity<String> createContest(@RequestPart ContestRequestDto requestDto, @RequestParam MultipartFile image){
        Contest contest = contestService.createContest(requestDto, image);
        return ResponseEntity.ok("upload success");
    }

    // 전체 목록 조회
    @GetMapping("/contestlist")
    public List<ContestListResponseDto> getAllContests() {
        return contestService.findAllContests();
    }

    // 글 수정 시 특정 글 정보 반환
    @GetMapping("/update")
    public ContestResponseDto getOneContest(@RequestBody Long id) {
        return contestService.findOneContest(id);
    }

    // 글 수정
    @PutMapping("/update")
    public ResponseEntity<String> updateContest(@RequestPart IdDto idDto, @RequestPart ContestRequestDto requestDto, @RequestParam MultipartFile image) {
        contestService.updateContest(idDto.getId(), requestDto, image);
        return ResponseEntity.ok("success");
    }

    // 글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteContest(@RequestBody IdDto idDto) {
        contestService.deleteContest(idDto.getId());
        return ResponseEntity.ok("success");
    }

    @PutMapping("/liked")
    public ResponseEntity<String> likedContest(@RequestBody IdDto idDto) {
        contestService.likeContest(idDto.getId());
        return ResponseEntity.ok("success");
    }
}