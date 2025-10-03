package com.m51.article.service.impl;

import com.m51.article.service.InteractionService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    public Long getViewCount(Long articleId) {
        Object obj=template.opsForValue().get(viewCountKey(articleId));
        return Long.parseLong(obj!=null ? obj+"" : "0");
    }
}
