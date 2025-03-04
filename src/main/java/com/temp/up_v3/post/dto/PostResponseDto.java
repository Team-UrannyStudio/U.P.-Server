package com.temp.up_v3.post.dto;

import com.temp.up_v3.post.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {

    private Long id;
    private String title;
    private String category;
    private String host;
    private String participants;
    private String start;
    private String finish;
    private String apply;
    private String price;
    private String location;
    private String prize;
    private String homepage;
    private String apply_link;
    private String contact;
    private LocalDateTime created_at;
    private String image_path;
    private String member_id;
    private Long comment_num;
    private String working;
    private String cut_line;
    private String advantage;
    private String main_content;
    private Long like_num;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.host = post.getHost();
        this.participants = post.getParticipants();
        this.start = post.getStart();
        this.finish = post.getFinish();
        this.apply = post.getApply();
        this.price = post.getPrice();
        this.location = post.getLocation();
        this.prize = post.getPrize();
        this.homepage = post.getHomepage();
        this.apply_link = post.getApply_link();
        this.contact = post.getContact();
        this.created_at = post.getCreated_at();
        this.image_path = post.getImage_path();
        this.member_id = post.getMember().getUid();
        this.comment_num = post.getComment_num();
        this.working = post.getWorking();
        this.cut_line = post.getCut_line();
        this.advantage = post.getAdvantage();
        this.main_content = post.getMain_content();
        this.like_num = post.getLike_num();
    }
}
