package com.m51.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.m51.article.entity.Article;
import com.m51.article.service.IArticleService;
import com.m51.article.service.InteractionService;
import com.m51.article.vo.ArticleQuery;
import com.m51.common.util.JwtUtil;
import com.m51.common.vo.Result;
import com.m51.file.utils.MinioUtils;
import com.m51.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private InteractionService interactionService;
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

    @Autowired
    private MinioUtils minioUtils;

//    @Operation(summary = "paginated query for articles")
//    @PostMapping("/list")
//    public Result<Map<String,Object>> getArticleList(@RequestBody ArticleQuery query){
//        Page<Article> page=articleService.getArticleList(query);
//        List<Article> articleList=page.getRecords();
//        //the avatar attribute stores the filename, but we need url to get the file
//        articleList=articleList.stream().map(item->{
//            item.setAvatar(minioUtils.getUrl(item.getAvatar()));
//            return item;
//        }).collect(Collectors.toList());
//        Map<String,Object> data=new HashMap<>();
//        data.put("total",page.getTotal());
//        data.put("rows",articleList);
//        return Result.Success(data);
//    }

    @Operation(summary = "paginated query for article")
    @PostMapping("/list")
    public Result<Map<String,Object>> getArticleList(@RequestBody ArticleQuery query){
        Page<Article> page = articleService.getArticleList(query);
        long total = page.getTotal();
        List<Article> records = page.getRecords();
        records=records.stream().map(item ->{
            item.setAvatar(minioUtils.getUrl(item.getAvatar()));
            return item;
        }).toList();
        Map<String,Object> data=new HashMap();
        data.put("total",total);
        data.put("rows",records);
        return Result.Success(data);

    }

    @Operation(summary = "returns the detail of an article")
    @GetMapping("/{id}")
    public Result<Article> getArticle(@PathVariable Long id,@RequestHeader ("Authorization") String token){
        User user=jwtUtil.parseJwt(token, User.class);
        Article article=articleService.getArticle(id);
        Long count=interactionService.viewCountIncrement(id);
        Boolean isFollowing=interactionService.isFollowing(article.getAuthorId(), user.getId());
        Map<String,Object> other=new HashMap<String,Object>();
        other.put("viewCount",count);
        other.put("isFollowing",isFollowing);
        article.setAvatar(minioUtils.getUrl(article.getAvatar()));
        article.setOther(other);
        return Result.Success(article);
    }

    @Operation(summary = "follow")
    @GetMapping("/follow/{authorId}")
    public Result<Object> follow(@PathVariable("authorId") Integer authorId,@RequestHeader("Authorization") String token){
        User user=jwtUtil.parseJwt(token, User.class);

        if(interactionService.follow(authorId, user.getId())){
            return Result.Success();
        }
        return Result.Fail();
    }

    @Operation(summary = "unfollow")
    @GetMapping("/unfollow/{authorId}")
    public Result<Object> unfollow(@PathVariable("authorId") Integer authorId,@RequestHeader("Authorization") String token){
        User user=jwtUtil.parseJwt(token, User.class);

        if(interactionService.unfollow(authorId, user.getId())){
            return Result.Success();
        }
        return Result.Fail();
    }
}
