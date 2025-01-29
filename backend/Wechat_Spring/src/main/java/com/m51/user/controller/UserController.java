package com.m51.user.controller;

import com.m51.common.vo.Result;
import com.m51.user.entity.User;
import com.m51.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sean
 * @since 18:02:33
 */
@Tag(name = "User",description = "User interface list")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;

    @Operation(summary = "username count",description = "get count of username")
    @GetMapping("/count/{username}")
    public Result<Integer> getCountByUsername(@PathVariable("username") String username){
        Integer count= service.getCountByUsername(username);
        return Result.Success(count);

    }

    @Operation(summary = "send code",description = "send verification code to the email")
    @GetMapping("/verification/{email}/{type}")
    public Result<String> sendCode(@PathVariable("email") String email,
                                   @PathVariable("type") String type){
        service.sendCode(email,type);

        return Result.Success("",null);
    }

    @Operation(summary = "check captcha code",description = "check if the input code is right")
    @GetMapping("/checkCode/{code}/{email}")
    public Result<Object> checkCode(@PathVariable("code") String code,
                                    @PathVariable("email") String email){
        String msg=service.checkCode(code,email);
        if(msg==null)
            return Result.Success();
        else
            return Result.Success(msg,null);
    }

    @Operation(summary = "user registration")
    @PostMapping("/register")
    public Result<User> userRegister(@RequestBody User user){
        service.userRegister(user);
        return Result.Success(user);
    }

    @Secured("ROLE_lv9")
    @Operation(summary = "get the list of all users")
    @GetMapping("/all")
    public Result<List<User>> getAll(){
        List<User> list = service.list();
        return Result.Success(list);
    }

    @Operation(summary = "get info of a certain user")
    @GetMapping("/info/{token}")
    public Result<User> getUserInfo(@PathVariable("token") String token){
        User user=service.getUser(token);
        if(user!=null)
            return Result.Success(user);
        else
            return Result.fail(20003,"user not logged in ");
    }


}
