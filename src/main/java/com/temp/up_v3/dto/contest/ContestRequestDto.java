package com.temp.up_v3.dto.contest;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContestRequestDto {
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
}
