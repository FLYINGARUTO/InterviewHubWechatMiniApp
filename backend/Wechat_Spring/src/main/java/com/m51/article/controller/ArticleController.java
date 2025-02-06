package com.m51.article.controller;

import com.m51.article.entity.Article;
import com.m51.article.service.IArticleService;
import com.m51.common.util.JwtUtil;
import com.m51.common.vo.Result;
import com.m51.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sean
 * @since 18:01:50
 */
@Tag(name = "Article",description = "Article related Functions")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private JwtUtil jwtUtil;
    @Operation(summary = "publish an article")
    @PostMapping("/publish")
    public Result<Object> publish(@RequestBody Article articleBody, @RequestHeader("Authorization") String token){
        User user=jwtUtil.parseJwt(token, User.class);
        if(articleService.publish(articleBody,user))
            return Result.Success("published successfully",null);
        else
            return Result.Fail("publish failed");
    }


}
