package com.temp.up_v3.comment;

import com.temp.up_v3.jwt.Member;
import com.temp.up_v3.comment.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static org.hibernate.type.descriptor.java.CoercionHelper.toLong;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private Long parentId;

    @Column(nullable = false)
    private String parent_type;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private String mainContent;

    @Column
    private Long like_num;

    @Column
    private Long dislike_num;

    public Comment(CommentRequestDto commentRequestDto) {
        this.created_at = LocalDateTime.now();
        this.mainContent = commentRequestDto.getMainContent();
        this.parentId = commentRequestDto.getParentId();
        this.parent_type = commentRequestDto.getParent_type();
        this.like_num = toLong(0);
        this.dislike_num = toLong(0);
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.mainContent = commentRequestDto.getMainContent();
    }
}
