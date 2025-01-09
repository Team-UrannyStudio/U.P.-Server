package com.temp.up_v3.dto.contest;

import com.temp.up_v3.domain.Contest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContestResponseDto {

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
    private String applyLink;
    private String contact;
    private String etc;
    private LocalDateTime created_at;
    public ContestResponseDto(Contest contest) {
        this.title = contest.getTitle();
        this.category = contest.getCategory();
        this.host = contest.getHost();
        this.participants = contest.getParticipants();
        this.start = contest.getStart();
        this.finish = contest.getFinish();
        this.apply = contest.getApply();
        this.price = contest.getPrice();
        this.location = contest.getLocation();
        this.prize = contest.getPrize();
        this.homepage = contest.getHomepage();
        this.applyLink = contest.getApplyLink();
        this.contact = contest.getContact();
        this.etc = contest.getEtc();
        this.created_at = contest.getCreated_at();
    }
}
