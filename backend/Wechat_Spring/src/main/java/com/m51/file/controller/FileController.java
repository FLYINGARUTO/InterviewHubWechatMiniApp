package com.m51.file.controller;

import com.m51.common.vo.Result;
import com.m51.file.utils.MinioUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Tag(name = "File oepration")
@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {
    @Autowired
    private MinioUtils minioUtils;

    @Operation(summary = "file upload")
    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public Result<Map<String,Object>> upload(@RequestPart("file") MultipartFile file){
        if(file==null || file.getSize()==0)
            return Result.fail(20010,"File is empty or null");
        try{
            Map<String, Object> uploadResult = minioUtils.upload(file);
            return Result.Success(uploadResult);

        }catch (Exception e){
            log.error(e.getMessage());
            return Result.fail(20010,"File is empty or null");
        }
    }
    @GetMapping("/url/{fileName}")
    @Operation(summary = "get the access url of the file")
    public Result<String> getFileUrl(@PathVariable("fileName") String filename){
        String url= minioUtils.getUrl(filename);
        return Result.Success(url);
    }
}