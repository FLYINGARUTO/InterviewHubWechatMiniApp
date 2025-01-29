package com.m51.base.mapper;

import com.m51.base.entity.BaseParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serial;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sean
 * @since 18:03:08
 */
public interface BaseParamMapper extends BaseMapper<BaseParam> {

    @Select("select * from m_base_param where base_name=#{baseName}")
    List<BaseParam> getBaseParamByBaseName(String baseName);
}
