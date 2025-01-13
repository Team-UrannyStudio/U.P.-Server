package com.temp.up_v3.controller;

import com.temp.up_v3.domain.Contest;
import com.temp.up_v3.domain.Member;
import com.temp.up_v3.dto.contest.ContestListResponseDto;
import com.temp.up_v3.dto.contest.ContestRequestDto;
import com.temp.up_v3.dto.contest.ContestResponseDto;
import com.temp.up_v3.service.ContestService;
import com.temp.up_v3.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
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

    // 글 하나 조회
    @GetMapping("/findOne")
    public ContestResponseDto getOneContest(@RequestBody Long id) {
        return contestService.findOneContest(id);
    }

    // 글 수정
    @PutMapping("/update")
    public ResponseEntity<String> updateContest(@RequestBody Long id, @RequestBody ContestRequestDto requestDto) {
        contestService.updateContest(id,requestDto);
        return ResponseEntity.ok("success");
    }

    // 글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteContest(@RequestBody Long id) {
        contestService.deleteContest(id);
        return ResponseEntity.ok("success");
    }
}