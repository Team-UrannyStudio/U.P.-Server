package com.temp.up_v3.dto.community;

import com.temp.up_v3.domain.Community;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommunityResponseDto {

    private Long id;
    private String title;
    private String memberId;
    private LocalDateTime created_at;
    private String imagePath;
    private String comment_num;
    private String mainContent;

    public CommunityResponseDto(Community community) {
        this.id = community.getId();
        this.title = community.getTitle();
        this.memberId = community.getMember().getUid();
        this.created_at = community.getCreated_at();
        this.comment_num = community.getComment_num();
        this.mainContent = community.getMainContent();
    }
}
