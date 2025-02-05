package com.temp.up_v3.dto.help;

import com.temp.up_v3.domain.Help;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HelpResponseDto {

    private String title;
    private String category;
    private String working;
    private String limitPeople;
    private String advantage;
    private String contactLink;
    private String mainLetter;
    private Long like_num;
    private LocalDateTime created_at;
    private String imagePath;

    public HelpResponseDto(Help help) {

        this.title = help.getTitle();
        this.category = help.getCategory();
        this.working = help.getWorking();
        this.limitPeople = help.getLimitPeople();
        this.advantage = help.getAdvantage();
        this.contactLink = help.getContactLink();
        this.mainLetter = help.getMainLetter();
        this.like_num = help.getLike_num();
        this.created_at = help.getCreated_at();
    }
}
