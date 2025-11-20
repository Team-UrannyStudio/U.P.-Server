package com.example.up_final.domain.comment.presentation;

import com.example.up_final.domain.comment.domain.enums.ContentType;
import com.example.up_final.domain.comment.presentation.dto.req.CommentCreateReq;
import com.example.up_final.domain.comment.presentation.dto.req.CommentUpdateReq;
import com.example.up_final.domain.comment.presentation.dto.res.CommentListRes;
import com.example.up_final.domain.comment.service.CommentService;
import com.example.up_final.global.common.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post")
    public Response createComment(@RequestBody CommentCreateReq req){
        return commentService.saveComment(req);
    }

    @GetMapping("/list")
    public List<CommentListRes> getComments(Long parentId, ContentType parentType) {
        return commentService.findComments(parentId, parentType);
    }

    @PutMapping("/{id}")
    public Response updateComment(@PathVariable("id") Long commentId, @RequestBody CommentUpdateReq req) {
        return commentService.updateComment(commentId, req);
    }

    @DeleteMapping("/{id}")
    public Response deleteComment(@PathVariable("id") Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @PutMapping("/liked/{id}")
    public Response likedComment(@PathVariable("id") Long commentId) {
        return commentService.likeComment(commentId);
    }

    @PutMapping("/disliked/{id}")
    public Response dislikedComment(@PathVariable("id") Long commentId) {
        return commentService.disLikeComment(commentId);
    }
}
