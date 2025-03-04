package com.temp.up_v3.comment.dto;

import lombok.Data;

@Data
public class CommentRequestDto {
    private String mainContent;
    private Long parentId;
    private String parent_type;
}
