package com.example.up_final.comment.dto;

import lombok.Data;

@Data
public class CommentRequestDto {
    private String mainContent;
    private Long parentId;
    private String parent_type;
}
