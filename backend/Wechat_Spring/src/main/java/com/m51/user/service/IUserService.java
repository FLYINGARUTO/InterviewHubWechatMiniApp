package com.m51.user.service;

import com.m51.common.vo.Result;
import com.m51.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sean
 * @since 18:02:33
 */
public interface IUserService extends IService<User> {

    Integer getCountByUsername(String username);

    void sendCode(String email,String type);

    String checkCode(String code, String email);

    void userRegister(User user);

    User getUser(String token);
}
