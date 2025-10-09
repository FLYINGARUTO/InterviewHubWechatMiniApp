package com.m51.user.mapper;

import com.m51.common.vo.Result;
import com.m51.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sean
 * @since 18:02:33
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select count(1) from m_user where username=#{username}")
    Integer getCountByUsername(String username);
    @Select("select * from m_user where username=#{username}")
    User getUserByUsername(String username);
    @Update("update m_user set avatar=#{avatar} where id=#{userId}")
    Integer updateAvatar(Integer userId,String avatar);
    @Select("select * from m_user where id=#{id}")
    User getUserById(Integer id);
}
