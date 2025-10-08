package com.m51.article.service.impl;

import com.m51.article.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InteractionServiceImpl implements InteractionService {

    @Autowired
    private RedisTemplate template;

    public String viewCountKey(Long id){
        return "view:"+id+":count";
    }
    // 粉丝列表（关注者列表）
    public String followersSetKey(Integer userId) {
        return "user:" + userId + ":followers";
    }

    // 关注列表
    public String followingSetKey(Integer userId) {
        return "user:" + userId + ":following";
    }

    @Override
    public Long viewCountIncrement(Long articleId) {
        return template.opsForValue().increment(viewCountKey(articleId));
    }

    @Override
    public Boolean follow(Integer authorId, Integer userId) {


//        Long followerCount= template.opsForSet().size(key);
        template.opsForSet().add(followersSetKey(authorId),userId);
        template.opsForSet().add(followingSetKey(userId),authorId);

        return true;
    }

    @Override
    public Boolean unfollow(Integer authorId, Integer userId) {

        template.opsForSet().remove(followersSetKey(authorId),userId);
        template.opsForSet().remove(followingSetKey(userId),authorId);
        return true;
    }

    @Override
    public Boolean isFollowing(Integer authorId, Integer userId) {
        String key = followersSetKey(authorId);
        return template.opsForSet().isMember(key,userId);
    }

    @Override
    public Long followerCount(Integer authorId) {
        return template.opsForSet().size(followersSetKey(authorId));
    }

    @Override
    public Long followingCount(Integer userId) {
        return template.opsForSet().size(followingSetKey(userId));
    }
    @Override
    public Long getViewCount(Long articleId) {
        Object obj=template.opsForValue().get(viewCountKey(articleId));
        return Long.parseLong(obj!=null ? obj+"" : "0");
    }


    private String articleLikeKey(Long articleId){
        return "article:"+articleId+":like";
    }
    private String authorLikeKey(Integer authorId){
        return "author:"+authorId+":like";
    }
    private String articleStarKey(Long articleId){
        return "article:"+articleId+":star";
    }
    private String authorStarKey(Integer authorId){
        return "author:"+authorId+":star";
    }
    @Override
    public Map<String, Object> like(Long articleId, Integer authorId, Integer userId) {
        //文章获赞列表
        template.opsForSet().add(articleLikeKey(articleId),userId);
        //作者获赞列表
        template.opsForSet().add(authorLikeKey(authorId),userId+'|'+articleId);

        return getInteraction(articleId,userId);
    }

    @Override
    public Map<String, Object> unlike(Long articleId, Integer authorId, Integer userId) {
        //文章获赞列表
        template.opsForSet().remove(articleLikeKey(articleId),userId);
        //作者获赞列表
        template.opsForSet().remove(authorLikeKey(authorId),userId+'|'+articleId);
        return getInteraction(articleId,userId);
    }

    @Override
    public Boolean isLike(Long articleId, Integer userId) {
        return template.opsForSet().isMember(articleLikeKey(articleId),userId);
    }

    @Override
    public Long getArticleLikeCount(Long articleId) {
        Long size=template.opsForSet().size(articleLikeKey(articleId));
        return size!=null ? size:0;
    }

    @Override
    public Long getAuthorLikeCount(Integer authorId) {
        Long size = template.opsForSet().size(authorLikeKey(authorId));
        return size!=null ?size:0;
    }

    @Override
    public Map<String, Object> star(Long articleId, Integer authorId, Integer userId) {
        template.opsForSet().add(articleStarKey(articleId),userId);
        template.opsForSet().add(authorStarKey(authorId),userId+"|"+articleId);
        return getInteraction(articleId,userId);
    }

    @Override
    public Map<String, Object> unstar(Long articleId, Integer authorId, Integer userId) {
        template.opsForSet().remove(articleStarKey(articleId),userId);
        template.opsForSet().remove(authorStarKey(authorId),userId+"|"+articleId);
        return getInteraction(articleId,userId);
    }

    @Override
    public Boolean isStar(Long articleId, Integer userId) {
        return template.opsForSet().isMember(articleStarKey(articleId),userId);
    }

    @Override
    public Long getArticleStarCount(Long articleId) {
        Long size=template.opsForSet().size(articleStarKey(articleId));
        return size;
    }

    @Override
    public Long getAuthorStarCount(Integer authorId) {
        Long size=template.opsForSet().size(authorStarKey(authorId));
        return size;
    }



    @Override
    public Map<String, Object> getInteraction(Long articleId, Integer userId) {
        Map<String,Object> interaction=new HashMap<>();
        interaction.put("isLike",isLike(articleId,userId));
        interaction.put("likeCount",getArticleLikeCount(articleId));
        interaction.put("isStar",isStar(articleId,userId));
        interaction.put("starCount",getArticleStarCount(articleId));
        return interaction;
    }

    @Override
    public Map<String, Object> getUserStatistics(Integer userId) {
        Map<String,Object> statistics=new HashMap<>();
        statistics.put("fansCount",followerCount(userId));//粉丝
        statistics.put("followingCount",followingCount(userId));//关注
        statistics.put("likeCount",getAuthorLikeCount(userId));//获赞
        statistics.put("starCount",getAuthorStarCount(userId));//收藏
        return statistics;
    }
}
