package com.temp.up_v3.dto.contest;

import com.temp.up_v3.domain.Contest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContestListResponseDto {
    private Long id;
    private String title;
    private String category;
    private String memberId;
    private String start;
    private String finish;
    private Long like_num;
    private String imagePath;

    public ContestListResponseDto(Contest contest) {
        this.id = contest.getId();
        this.title = contest.getTitle();
        this.category = contest.getCategory();
        this.memberId = contest.getMember().getUid();
        this.start = contest.getStart();
        this.finish = contest.getFinish();
        this.like_num = contest.getLike_num();
    }
}
