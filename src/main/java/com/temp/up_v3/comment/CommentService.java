package com.temp.up_v3.comment;

import com.temp.up_v3.jwt.Member;
import com.temp.up_v3.comment.dto.CommentListResponseDto;
import com.temp.up_v3.comment.dto.CommentRequestDto;
import com.temp.up_v3.jwt.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public void createComment(CommentRequestDto requestDto) {

        Comment comment = new Comment(requestDto);

        comment.setMember(memberRepository.findMemberByUid(SecurityContextHolder.getContext().getAuthentication().getName()));

        commentRepository.save(comment);
    }

    public List<CommentListResponseDto> findAllComments (Long parentId, String parent_type) {

        List<Comment> commentList = commentRepository.findByParentId(parentId);
        List<CommentListResponseDto> responseDtoList = new ArrayList<>();

        Collections.reverse(commentList);

        Integer count = 0;

        for (Comment comment : commentList) {

            CommentListResponseDto responseDto = new CommentListResponseDto(comment);

            if (parent_type.equals(comment.getParent_type())) {

                responseDtoList.add(responseDto);
                count++;

            }
            if (count > 15) {
                break;
            }

        }

        return responseDtoList;
    }

    @Transactional
    public void updateComment(Long id, CommentRequestDto requestDto) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        comment.update(requestDto);
    }

    //글 삭제
    public void deleteComment(Long id) {

        commentRepository.deleteById(id);

    }

    @Transactional
    public void likeComment(Long commentId) {

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        Member member = memberRepository.findByUid(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        if (member.getLiked().contains("comment_" + commentId)) {

            member.getLiked().remove("comment_" + commentId);
            comment.setLike_num(comment.getLike_num() - 1);

        } else {

            member.getLiked().add("comment_" + commentId);
            comment.setLike_num(comment.getLike_num() + 1);

        }
    }

    @Transactional
    public void disLikeComment(Long commentId) {

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        Member member = memberRepository.findByUid(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        if (member.getDisLiked().contains(commentId)) {

            member.getDisLiked().remove(commentId);
            comment.setDislike_num(comment.getDislike_num() - 1);

        } else {

            member.getDisLiked().add(commentId);
            comment.setDislike_num(comment.getDislike_num() + 1);

        }
    }
}
