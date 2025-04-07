package com.temp.up_v3.post;

import com.temp.up_v3.post.dto.IdDto;
import com.temp.up_v3.post.dto.PostListResponseDto;
import com.temp.up_v3.post.dto.PostRequestDto;
import com.temp.up_v3.post.dto.PostResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/content")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    //정상 작동 확인 코드
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 글 등록
    @PostMapping("/post")
    public ResponseEntity<String> createContent(@RequestPart PostRequestDto requestDto, @RequestPart String content_type, @RequestParam MultipartFile image){
        postService.createContent(requestDto, content_type, image);
        return ResponseEntity.ok("upload success");
    }

    // 전체 목록 조회
    @GetMapping("/contentList")
    public List<PostListResponseDto> getAllContents(@RequestParam String content_type) {
        System.out.println(content_type);
        return postService.findAllContents(content_type);
    }

    //검색 조회
    @GetMapping("/search")
    public List<PostListResponseDto> getSearchedContents(@RequestParam String search, String content_type) {
        return postService.findSearchedContents(search, content_type);
    }

    //카테고리 검색 조회
    @GetMapping("/searchByCategory")
    public List<PostListResponseDto> getContentsByCategory(@RequestParam String category, String participants, String location, String content_type) {
        return postService.findByCategory(category, participants, location, content_type);
    }

    @GetMapping("/getOne")
    public PostResponseDto getOne(@RequestParam Long id) {
        return postService.findOneContent(id);
    }

    // 글 수정 시 특정 글 정보 반환
    @GetMapping("/update")
    public PostResponseDto getOneContent(@RequestParam Long id) {
        return postService.findOneContent(id);
    }

    // 글 수정
    @PutMapping("/update")
    public ResponseEntity<String> updateContent(@RequestPart IdDto idDto, @RequestPart PostRequestDto requestDto, @RequestParam MultipartFile image) {
        postService.updateContent(idDto.getId(), requestDto, image);
        return ResponseEntity.ok("success");
    }

    // 글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteContent(@RequestPart IdDto idDto) {
        postService.deleteContent(idDto.getId());
        return ResponseEntity.ok("success");
    }

    @PutMapping("/liked")
    public ResponseEntity<String> likedContent(@RequestPart IdDto idDto) {
        postService.likeContent(idDto.getId());
        return ResponseEntity.ok("success");
    }
}