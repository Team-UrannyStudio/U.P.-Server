package com.temp.up_v3.domain;

import com.temp.up_v3.dto.community.CommunityRequestDto;
import com.temp.up_v3.dto.community.CommunityResponseDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static org.hibernate.type.descriptor.java.CoercionHelper.toLong;

@Entity
@Data
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String mainContent;

    @Column
    private Long comment_num;

    @Column
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    public Community(CommunityRequestDto communityRequestDto) {
        this.title = communityRequestDto.getTitle();
        this.category = communityRequestDto.getCategory();
        this.mainContent = communityRequestDto.getMainContent();
        this.comment_num = toLong(0);
        this.created_at = LocalDateTime.now();
    }

    public void update(CommunityRequestDto communityRequestDto) {
        this.title = communityRequestDto.getTitle();
        this.category = communityRequestDto.getCategory();
        this.mainContent = communityRequestDto.getMainContent();
    }
}
