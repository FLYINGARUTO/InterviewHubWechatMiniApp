package com.m51.base.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.m51.base.entity.BaseParam;
import com.m51.base.service.IBaseParamService;
import com.m51.common.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sean
 * @since 18:03:08
 */
@Tag(name="Base",description = "Base interface list")
@RestController
@RequestMapping("/base")
public class BaseParamController {
    @Autowired
    private IBaseParamService service;

    @GetMapping
    public Result<List<BaseParam>> getAll(){
        List<BaseParam> list=service.list();
        return Result.Success(list);
    }
    @Operation(summary = "get parameter list",description = "get parameter list to render the 'register' page")
    @GetMapping("/{baseName}")
    public Result<List<BaseParam>> getBaseParamByBaseName(@PathVariable("baseName") String baseName){
        List<BaseParam> list=service.getBaseParamByBaseName(baseName);
        return Result.Success(list);
    }
}
