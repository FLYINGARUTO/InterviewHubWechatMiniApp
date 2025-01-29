package com.m51.base.service.impl;

import com.m51.base.entity.BaseParam;
import com.m51.base.mapper.BaseParamMapper;
import com.m51.base.service.IBaseParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sean
 * @since 18:03:08
 */
@Service
public class BaseParamServiceImpl extends ServiceImpl<BaseParamMapper, BaseParam> implements IBaseParamService {

    @Override
    public List<BaseParam> getBaseParamByBaseName(String baseName) {
        return this.baseMapper.getBaseParamByBaseName(baseName);
    }
}
