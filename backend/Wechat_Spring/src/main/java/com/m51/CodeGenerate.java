package com.m51;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerate {
    public static void main(String[] args) {
        String url = "jdbc:mysql:///wechat_mini";
        String username = "root";
        String password = "lx120688";
        String outputDir = "/Users/flyingbuta/Desktop/Wechat_Spring/src/main/java";
        String moduleName = "base";
        String mapperXml = "/Users/flyingbuta/Desktop/Wechat_Spring/src/main/resources" +
                "/mapper/" + moduleName ;
        String tables = "m_base_param";
        String tablePrefix = "m_";
        String parent = "com.m51";

        FastAutoGenerator.create(url, username, password)

                .globalConfig(builder -> {
                    builder.author("Sean") // 设置作者
//.enableSwagger() // 开启 swagger 模式
//.fileOverride() // 覆盖已生成文件
                            .commentDate("HH:mm:ss")
                            .outputDir(outputDir); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
// 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        }))
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    mapperXml)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认的是Velocity引擎模板

                .execute();
    }
}