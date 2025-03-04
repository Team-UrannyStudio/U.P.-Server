package com.temp.up_v3.jwt.dto;

import com.temp.up_v3.comment.Comment;
import com.temp.up_v3.comment.dto.CommentListResponseDto;
import com.temp.up_v3.jwt.Member;
import com.temp.up_v3.post.Post;
import com.temp.up_v3.post.dto.PostListResponseDto;
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
