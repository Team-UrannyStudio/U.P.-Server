package com.temp.up_v3.dto.help;

import com.temp.up_v3.domain.Help;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HelpListResponseDto {

    private Long id;
    private String title;
    private String working;
    private String memberId;
    private String category;
    private String imagePath;
    private Long like_num;

    public HelpListResponseDto(Help help) {
        this.id = help.getId();
        this.title = help.getTitle();
        this.working = help.getWorking();
        this.memberId = help.getMember().getUid();
        this.category = help.getCategory();
        this.like_num = help.getLike_num();
    }

}
