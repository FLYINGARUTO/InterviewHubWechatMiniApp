package com.m51.article.vo;

import com.m51.common.vo.MyPage;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

@Data
public class ArticleQuery extends MyPage {
    private String title;
    private Integer categoryId;
}
