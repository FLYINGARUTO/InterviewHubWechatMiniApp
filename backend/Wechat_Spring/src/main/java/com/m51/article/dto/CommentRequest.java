package com.m51.article.dto;

import lombok.Data;

@Data
public class CommentRequest {
    Long articleId;
    int pageNo;
    int pageSize;
}
