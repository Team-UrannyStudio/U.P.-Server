package com.temp.up_v3.controller;

import com.temp.up_v3.domain.Help;
import com.temp.up_v3.dto.help.HelpListResponseDto;
import com.temp.up_v3.dto.help.HelpRequestDto;
import com.temp.up_v3.dto.help.HelpResponseDto;
import com.temp.up_v3.dto.others.IdDto;
import com.temp.up_v3.service.HelpService;
import com.temp.up_v3.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/help")
@AllArgsConstructor
public class HelpController {

    private final HelpService helpService;
    private final ImageService imageService;


    // 글 등록
    @PostMapping("/post")
    public ResponseEntity<String> createHelp(@RequestPart HelpRequestDto requestDto, @RequestParam MultipartFile image){
        Help help = helpService.createHelp(requestDto, image);
        return ResponseEntity.ok("upload success");
    }

    // 전체 목록 조회
    @GetMapping("/helplist")
    public List<HelpListResponseDto> getAllHelps() {
        return helpService.findAllHelps();
    }

    // 글 수정 시 특정 글 정보 반환
    @GetMapping("/update")
    public HelpResponseDto getOneHelp(@RequestBody Long id) {
        return helpService.findOneHelp(id);
    }

    // 글 수정
    @PutMapping("/update")
    public ResponseEntity<String> updateHelp(@RequestPart IdDto idDto, @RequestPart HelpRequestDto requestDto, @RequestParam MultipartFile image) {
        helpService.updateHelp(idDto.getId(), requestDto, image);
        return ResponseEntity.ok("success");
    }

    // 글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHelp(@RequestBody IdDto idDto) {
        helpService.deleteHelp(idDto.getId());
        return ResponseEntity.ok("success");
    }

    @PutMapping("/liked")
    public ResponseEntity<String> likedHelp(@RequestBody IdDto idDto) {
        helpService.likeHelp(idDto.getId());
        return ResponseEntity.ok("success");
    }
}