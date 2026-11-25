package com.github.shuaidd.file;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Objects;

/**
 * 描述 上传文件：替代 5.x 中 Spring 的 {@code MultipartFile}，内容为字节数组，附带文件名与（可选的）Content-Type
 * <p>
 * 直接持有调用方传入的字节数组，构造后请勿再修改其内容。
 *
 * @author ddshuai
 * date 2026-11-25 23:40
 **/
public class QywxFile {

    private final byte[] content;

    private final String filename;

    private final String contentType;

    public QywxFile(byte[] content, String filename) {
        this(content, filename, null);
    }

    public QywxFile(byte[] content, String filename, String contentType) {
        this.content = Objects.requireNonNull(content, "content");
        this.filename = Objects.requireNonNull(filename, "filename");
        this.contentType = StringUtils.isBlank(contentType) ? null : contentType;
    }

    /**
     * 读取本地文件；Content-Type 先按系统探测，探测不到再按文件名猜测
     *
     * @param file 本地文件
     * @return 上传文件
     * @throws IOException 读取失败
     */
    public static QywxFile of(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        String detected = Files.probeContentType(file.toPath());
        if (StringUtils.isBlank(detected)) {
            detected = URLConnection.guessContentTypeFromName(file.getName());
        }
        return new QywxFile(Files.readAllBytes(file.toPath()), file.getName(), detected);
    }

    public byte[] getContent() {
        return content;
    }

    public String getFilename() {
        return filename;
    }

    /**
     * 文件类型；未指定时为 null，由编码器按文件名猜测，仍猜不到则用 application/octet-stream
     *
     * @return Content-Type
     */
    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "QywxFile{filename='" + filename + "', contentType=" + contentType + ", size=" + content.length + '}';
    }
}
