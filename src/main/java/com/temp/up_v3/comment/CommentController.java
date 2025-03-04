package com.temp.up_v3.comment;

import com.temp.up_v3.comment.dto.CommentListResponseDto;
import com.temp.up_v3.comment.dto.CommentRequestDto;
import com.temp.up_v3.post.dto.IdDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 글 등록
    @PostMapping("/post")
    public ResponseEntity<String> createComment(@RequestPart CommentRequestDto requestDto){
        requestDto.setParent_type("content");
        commentService.createComment(requestDto);
        return ResponseEntity.ok("upload success");
    }

    //답글 등록
    @PostMapping("/comment")
    public ResponseEntity<String> createReComment(@RequestPart CommentRequestDto requestDto){
        requestDto.setParent_type("comment");
        commentService.createComment(requestDto);
        return ResponseEntity.ok("upload success");
    }

    // 전체 목록 조회
    @GetMapping("/commentlist")
    public List<CommentListResponseDto> getAllComments(Long parentId) {
        return commentService.findAllComments(parentId, "content");
    }

    // 답글 목록 조회
    @PostMapping("/reCommentList")
    public List<CommentListResponseDto> getAllReComments(Long parentId) {
        return commentService.findAllComments(parentId, "comment");
    }

    // 글 수정
    @PutMapping("/update")
    public ResponseEntity<String> updateComment(@RequestPart IdDto idDto, @RequestPart CommentRequestDto requestDto) {
        commentService.updateComment(idDto.getId(), requestDto);
        return ResponseEntity.ok("success");
    }

    // 글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestBody IdDto idDto) {
        commentService.deleteComment(idDto.getId());
        return ResponseEntity.ok("success");
    }

    @PutMapping("/liked")
    public ResponseEntity<String> likedComment(@RequestBody IdDto idDto) {
        commentService.likeComment(idDto.getId());
        return ResponseEntity.ok("success");
    }

    @PutMapping("/disliked")
    public ResponseEntity<String> dislikedComment(@RequestBody IdDto idDto) {
        commentService.disLikeComment(idDto.getId());
        return ResponseEntity.ok("success");
    }
}
