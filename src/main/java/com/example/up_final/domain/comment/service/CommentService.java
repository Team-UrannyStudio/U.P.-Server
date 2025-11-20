package com.example.up_final.domain.comment.service;

import com.example.up_final.domain.comment.domain.Comment;
import com.example.up_final.domain.comment.domain.enums.ContentType;
import com.example.up_final.domain.comment.presentation.dto.req.CommentUpdateReq;
import com.example.up_final.domain.comment.presentation.dto.res.CommentListRes;
import com.example.up_final.domain.comment.presentation.dto.req.CommentCreateReq;
import com.example.up_final.domain.comment.domain.repo.CommentRepository;
import com.example.up_final.global.common.response.Response;
import com.example.up_final.global.jwt.auth.UserSessionHolder;
import com.example.up_final.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;
    private final UserSessionHolder userSessionHolder;

    public Response saveComment(CommentCreateReq req) {
        Member writer = userSessionHolder.getUser();
        Comment comment = CommentCreateReq.toComment(req, writer);

        commentRepo.save(comment);

        return Response.created("댓글이 정상적으로 생성되었습니다.");
    }

    public List<CommentListRes> findComments (Long parentId, ContentType parentType) {
        return commentRepo.findByParentIdAndParentType(parentId, parentType)
                .stream()
                .map(CommentListRes::from)
                .toList();
    }

    public Response updateComment(Long commentId, CommentUpdateReq req) {
        Comment comment = commentRepo.findById(commentId).orElseThrow();

        comment.update(req);

        return Response.ok("정상적으로 수정되었습니다.");
    }

    public Response deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);

        return Response.ok("정상적으로 삭제되었습니다.");
    }

    public Response likeComment(Long commentId) {
        Member presentUser = userSessionHolder.getUser();
        Comment comment = commentRepo.findById(commentId).orElseThrow();

        boolean isLiked = presentUser.getLikedComment().contains(commentId);

        if (isLiked) {
            presentUser.getLikedComment().remove(commentId);
        } else {
            presentUser.getLikedComment().add(commentId);
        }

        comment.updateLike_num(comment.getLike_num() + (isLiked ? -1 : 1));

        return Response.ok("좋아요!");
    }

    public Response disLikeComment(Long commentId) {
        Member presentUser = userSessionHolder.getUser();
        Comment comment = commentRepo.findById(commentId).orElseThrow();

        boolean isDisliked = presentUser.getDislikedComment().contains(commentId);

        if (isDisliked) {
            presentUser.getDislikedComment().remove(commentId);
        } else {
            presentUser.getDislikedComment().add(commentId);
        }

        comment.updateDislike_num(comment.getDislike_num() + (isDisliked ? -1 : 1));

        return Response.ok("싫어요 ㅡㅡ");
    }
}
