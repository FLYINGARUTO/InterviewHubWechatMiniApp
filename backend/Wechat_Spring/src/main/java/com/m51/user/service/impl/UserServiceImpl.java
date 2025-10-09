package com.m51.user.service.impl;

import com.m51.common.util.JwtUtil;
import com.m51.common.vo.Result;
import com.m51.file.utils.MinioUtils;
import com.m51.user.entity.User;
import com.m51.user.mapper.UserMapper;
import com.m51.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sean
 * @since 18:02:33
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private AmqpTemplate amqpTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MinioUtils minioUtils;
    @Override
    public Integer getCountByUsername(String username) {
        return this.baseMapper.getCountByUsername(username);
    }

    @Override
    public void sendCode(String email, String type) {
        Random random=new Random();
        String code=String.format("%06d",random.nextInt(1000000));
        Map<String,Object> map= Map.of("type",type,"email",email,"code",code);
        //amqpTemplate.convertAndSend("mail",map);
        redisTemplate.opsForValue().set(concatRedisKey(email),code);
        System.out.println("Verification code is "+code);
        System.out.println("");
    }

    @Override
    public String checkCode(String code, String email) {
        String realCode = (String) redisTemplate.opsForValue().get(concatRedisKey(email));
        if(realCode==null) return "click 'send'";
        if(!realCode.equals(code)) return "wrong code";
        return null;
    }

    private String concatRedisKey(String email){
        return "captcha:"+email;
    }
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User getUser(String token) {
        User user=jwtUtil.parseJwt(token, User.class);
        return user;
    }

    @Override
    public void userRegister(User user) {
        user.setPassword(encoder.encode(user.getPassword()));//encrypt the password
        user.setType(1);
        save(user);


    }

    @Override
    public User updateAvatar(Integer userId, String avatar) {
        if(baseMapper.updateAvatar(userId,avatar)==1){
            User user=baseMapper.getUserById(userId);
            user.setAvatarUrl(minioUtils.getUrl(user.getAvatar()));
            user.setPassword("");
            return user;
        }
        return null;

    }
}
