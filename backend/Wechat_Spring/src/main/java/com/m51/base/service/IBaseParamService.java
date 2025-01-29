package com.m51.base.service;

import com.m51.base.entity.BaseParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sean
 * @since 18:03:08
 */
public interface IBaseParamService extends IService<BaseParam> {

    List<BaseParam> getBaseParamByBaseName(String baseName);
}
