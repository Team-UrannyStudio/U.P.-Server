package com.temp.up_v3.comment;

import com.temp.up_v3.image.ImageRepository;
import com.temp.up_v3.jwt.Member;
import com.temp.up_v3.comment.dto.CommentListResponseDto;
import com.temp.up_v3.comment.dto.CommentRequestDto;
import com.temp.up_v3.jwt.MemberRepository;
import com.temp.up_v3.post.dto.PostListResponseDto;
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
    private final ImageRepository imageRepository;

    public void createComment(CommentRequestDto requestDto) {

        Comment comment = new Comment(requestDto);

        comment.setParent_type("content");

        comment.setMember(memberRepository.findMemberByUid(SecurityContextHolder.getContext().getAuthentication().getName()));

        commentRepository.save(comment);
    }

    public List<CommentListResponseDto> findAllComments (Long parentId, String parent_type) {

        List<Comment> commentList = commentRepository.findByParentId(parentId);
        List<CommentListResponseDto> responseDtoList = new ArrayList<>();

        Collections.reverse(commentList);

        for (Comment comment : commentList) {

            CommentListResponseDto responseDto = new CommentListResponseDto(comment);

            if (parent_type.equals(comment.getParent_type())) {

                responseDtoList.add(responseDto);

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

        if (member.getLikedComment().contains(commentId)) {

            member.getLikedComment().remove(commentId);
            comment.setLike_num(comment.getLike_num() - 1);

        } else {

            member.getLikedComment().add(commentId);
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

    @Transactional
    public List<CommentListResponseDto> Converter(List<Long> CommentList) {

        List<CommentListResponseDto> responseDtoList = new ArrayList<>();

        for (Long id : CommentList) {

            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("조회 실패")
            );

            CommentListResponseDto responseDto = new CommentListResponseDto(comment);

            responseDtoList.add(responseDto);

        }

        return responseDtoList;
    }
}
