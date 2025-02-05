package com.temp.up_v3.dto.community;

import com.temp.up_v3.domain.Community;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommunityListResponseDto {

    private Long id;
    private String title;
    private String category;
    private String memberId;
    private LocalDateTime created_at;
    private Long comment_num;

    public CommunityListResponseDto(Community community) {
        this.id = community.getId();
        this.title = community.getTitle();
        this.category = community.getCategory();
        this.memberId = community.getMember().getUid();
        this.created_at = community.getCreated_at();
        this.comment_num = community.getComment_num();
    }
}
