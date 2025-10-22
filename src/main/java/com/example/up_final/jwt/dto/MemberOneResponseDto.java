package com.example.up_final.jwt.dto;

import com.example.up_final.comment.dto.CommentListResponseDto;
import com.example.up_final.jwt.Member;
import com.example.up_final.post.dto.PostListResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class MemberOneResponseDto {
    private String uid;
    private String email;
    private String name;
    private String phone;
    private String address;
    private String profilePicture;
    private List<CommentListResponseDto> likedComment;
    private List<CommentListResponseDto> dislikedComment;
    private List<PostListResponseDto> likedContent;

    public MemberOneResponseDto(Member member) {
        this.uid = member.getUid();
        this.email = member.getEmail();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.profilePicture = member.getProfilePicture();
    }
}
