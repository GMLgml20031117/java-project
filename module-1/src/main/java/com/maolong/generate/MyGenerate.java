package com.maolong.generate;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;


public class MyGenerate {
    public static void main(String[] args) {
        String route="E:\\java\\project\\Java-Project\\module-1";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/java_project", "root", "gml20031117")
                .globalConfig(builder -> {
                    builder.author("maolong") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(route+"\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("maolong") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, route+"\\src\\main\\resources\\com\\maolong")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
