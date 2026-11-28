package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.file.QywxFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 Spring Web 类型与 core 文件类型的双向桥接：{@code MultipartFile} → {@link QywxFile}（上传），
 * {@link FileDownload} → {@code ResponseEntity<byte[]>}（下载）
 * <p>
 * core 不依赖 spring-web，所以这两段转换放在适配层：Controller 里收到 {@code MultipartFile} 先 {@link #toQywxFile}，
 * 拿到 {@link FileDownload} 后 {@link #toResponseEntity} 再返回。文件名取值顺序：原始文件名 → 表单字段名 → {@link #DEFAULT_FILENAME}；
 * 空白的 content-type 规范化为 null（与 core 的 {@link QywxFile} 一致）。本类只依赖 spring-web，{@code WeChatAutoConfiguration}
 * 本身不引用它，用户项目里没有 spring-web 时适配层照常装配。
 *
 * @author ddshuai
 * date 2026-11-28 02:00
 **/
public final class SpringFileBridge {

    public static final String DEFAULT_FILENAME = "file";

    private SpringFileBridge() {
    }

    /**
     * 上传：{@code MultipartFile} → {@link QywxFile}
     *
     * @param file Spring 收到的上传文件
     * @return core 的文件载体；文件名按「原始文件名 → 字段名 → {@link #DEFAULT_FILENAME}」回退
     * @throws IOException 读取文件内容失败
     */
    public static QywxFile toQywxFile(MultipartFile file) throws IOException {
        Objects.requireNonNull(file, "file");
        String filename = file.getOriginalFilename();
        if (!StringUtils.hasText(filename)) {
            filename = file.getName();
        }
        if (!StringUtils.hasText(filename)) {
            filename = DEFAULT_FILENAME;
        }
        return new QywxFile(file.getBytes(), filename, file.getContentType());
    }

    /**
     * 下载：{@link FileDownload} → 200 响应，头原样带过去（content-type / content-disposition 等），体是文件内容
     *
     * @param download core 下载得到的文件
     * @return 可直接从 Controller 返回的响应
     */
    public static ResponseEntity<byte[]> toResponseEntity(FileDownload download) {
        Objects.requireNonNull(download, "download");
        HttpHeaders headers = new HttpHeaders();
        for (Map.Entry<String, List<String>> header : download.getHeaders().entrySet()) {
            for (String value : header.getValue()) {
                headers.add(Objects.requireNonNull(header.getKey(), "header name"), value);
            }
        }
        return new ResponseEntity<>(download.getContent(), headers, HttpStatus.OK);
    }
}
