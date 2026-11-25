package com.github.shuaidd.client.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 指定文件上传参数在 multipart 表单中的字段名（5.x 取 {@code MultipartFile.getName()}，依赖调用方的表单字段名）
 * <p>
 * 标注在 {@code QywxFile} 类型的 body 参数上，例如 {@code @Part("media") QywxFile file}。
 *
 * @author ddshuai
 * date 2026-11-25 23:40
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Part {

    /**
     * multipart 表单字段名
     *
     * @return 字段名
     */
    String value();
}
