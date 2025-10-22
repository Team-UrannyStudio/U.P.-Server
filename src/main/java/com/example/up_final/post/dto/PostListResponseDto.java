package com.example.up_final.post.dto;

import com.example.up_final.post.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostListResponseDto {

    private Long id;
    private String title;
    private String category;
    private String member_id;
    private LocalDateTime created_at;
    private Long comment_num;
    private String start;
    private String finish;
    private Long like_num;
    private String image_path;
    private String working;
    private int page;

    public PostListResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.member_id = post.getMember().getUid();
        this.created_at = post.getCreated_at();
        this.comment_num = post.getComment_num();
        this.start = post.getStart();
        this.finish = post.getFinish();
        this.like_num = post.getLike_num();
        this.image_path = post.getImage_path();
        this.working = post.getWorking();
    }
}
