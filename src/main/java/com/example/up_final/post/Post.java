package com.example.up_final.post;

import com.example.up_final.jwt.Member;
import com.example.up_final.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static org.hibernate.type.descriptor.java.CoercionHelper.toLong;

@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String image_path;

    @Column
    private String category;

    @Column
    private String host;

    @Column
    private String participants;

    @Column
    private String start;

    @Column
    private String finish;

    @Column
    private String apply;

    @Column
    private String price;

    @Column
    private String location;

    @Column
    private String prize;

    @Column
    private String homepage;

    @Column
    private String apply_link;

    @Column
    private String contact;

    @Column
    private String working;

    @Column
    private String cut_line;

    @Column
    private String advantage;

    @Column
    private Long like_num;

    @Column
    private Long disLike_num;

    @Column
    private String main_content;

    @Column
    private Long comment_num;

    @Column
    private LocalDateTime created_at;

    @Column
    private String content_type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.host = requestDto.getHost();
        this.participants = requestDto.getParticipants();
        this.start = requestDto.getStart();
        this.finish = requestDto.getFinish();
        this.apply = requestDto.getApply();
        this.price = requestDto.getPrice();
        this.location = requestDto.getLocation();
        this.prize = requestDto.getPrize();
        this.homepage = requestDto.getHomepage();
        this.contact = requestDto.getContact();
        this.working = requestDto.getWorking();
        this.cut_line = requestDto.getCut_line();
        this.advantage = requestDto.getAdvantage();
        this.like_num = toLong(0);
        this.main_content = requestDto.getMain_content();
        this.comment_num = toLong(0);
        this.created_at = LocalDateTime.now();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.host = requestDto.getHost();
        this.participants = requestDto.getParticipants();
        this.start = requestDto.getStart();
        this.finish = requestDto.getFinish();
        this.apply = requestDto.getApply();
        this.price = requestDto.getPrice();
        this.location = requestDto.getLocation();
        this.prize = requestDto.getPrize();
        this.homepage = requestDto.getHomepage();
        this.apply_link = requestDto.getApply_link();
        this.contact = requestDto.getContact();
        this.working = requestDto.getWorking();
        this.cut_line = requestDto.getCut_line();
        this.advantage = requestDto.getAdvantage();
        this.main_content = requestDto.getMain_content();
    }
}
