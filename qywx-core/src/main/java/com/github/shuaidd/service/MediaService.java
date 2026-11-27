package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.client.WeChatMediaClient;
import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.file.QywxFile;
import com.github.shuaidd.response.addressbook.AsyncJobResponse;
import com.github.shuaidd.response.material.UploadByUrlResultResponse;
import com.github.shuaidd.response.material.UploadImageResponse;
import com.github.shuaidd.response.material.WeChatMediaUploadResponse;
import com.github.shuaidd.resquest.job.JobIdRequest;
import com.github.shuaidd.resquest.media.UploadByUrlRequest;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

/**
 * 描述 素材管理
 * <p>
 * 由 5.x 的同名 service 迁移而来：上传参数由 Spring 的 {@code MultipartFile} 改为 {@link QywxFile}，
 * {@code MediaType} 参数改为 {@code String contentType}，下载结果由 {@code ResponseEntity<byte[]>} 改为 {@link FileDownload}；
 * 方法签名除此之外不变。5.x 的 {@code File} 重载靠 commons-fileupload 的 {@code DiskFileItem} 中转，这里直接读成字节数组。
 *
 * @author ddshuai
 * date 2019-04-06 16:18
 **/
public class MediaService extends AbstractBaseService {

    public static final String FILE = "file";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";

    private final WeChatMediaClient weChatMediaClient;

    public MediaService(QywxContext context, WeChatMediaClient weChatMediaClient) {
        super(context);
        this.weChatMediaClient = weChatMediaClient;
    }

    /**
     * 上传临时素材
     *
     * @param file            文件
     * @param contentType     文件类型（Content-Type）
     * @param type            类型
     * @param applicationName 应用名称
     * @return media_id
     */
    public String uploadMaterial(File file, String contentType, String type, String applicationName) {
        String mediaId = null;
        if (Objects.nonNull(file) && Objects.nonNull(contentType)) {
            mediaId = uploadMaterial(readFile(file, contentType, applicationName), type, applicationName);
        }

        return mediaId;
    }

    /**
     * 上传临时素材
     * @param file 文件
     * @param type 媒体类型
     * @param applicationName 应用名称
     * @return media_id
     */
    public String uploadMaterial(QywxFile file, String type, String applicationName) {
        if (Objects.nonNull(file) && Objects.nonNull(type)) {
            WeChatMediaUploadResponse response = weChatMediaClient.uploadMaterial(file, type, applicationName);
            return response.getMediaId();
        }

        return null;
    }

    /**
     * 上传附件
     * @param file 文件
     * @param mediaType 媒体类型
     * @param attachmentType 附件类型
     * @param applicationName 应用名称
     * @return media_id
     */
    public String uploadAttachment(QywxFile file, String mediaType, Integer attachmentType, String applicationName) {
        if (Objects.nonNull(file) && Objects.nonNull(mediaType) && Objects.nonNull(attachmentType)) {
            WeChatMediaUploadResponse response = weChatMediaClient.uploadAttachment(file, mediaType, attachmentType, applicationName);
            return response.getMediaId();
        }

        return null;
    }

    /**
     * 上传附件
     * @param file 文件
     * @param mediaType 媒体类型
     * @param attachmentType 附件类型
     * @param applicationName 应用名称
     * @return media_id
     */
    public String uploadAttachment(File file, String mediaType, Integer attachmentType, String applicationName) {
        if (Objects.nonNull(file) && Objects.nonNull(mediaType) && Objects.nonNull(attachmentType)) {
            return uploadAttachment(readFile(file, "application/octet-stream", applicationName), mediaType, attachmentType, applicationName);
        }

        return null;
    }

    /**
     * 上传永久图片
     *
     * @param file            文件
     * @param contentType     文件类型（Content-Type）
     * @param applicationName 应用名称
     * @return 图片地址
     */
    public String uploadImage(File file, String contentType, String applicationName) {
        if (Objects.nonNull(file) && Objects.nonNull(contentType)) {
            UploadImageResponse response = weChatMediaClient.uploadImage(readFile(file, contentType, applicationName), applicationName);
            return response.getUrl();
        }

        return null;
    }

    /**
     * 读取本地文件为上传文件
     *
     * @param file            文件
     * @param contentType     文件类型（Content-Type）
     * @param applicationName 应用名称，仅用于日志
     * @return 上传文件
     */
    private QywxFile readFile(File file, String contentType, String applicationName) {
        try {
            return new QywxFile(Files.readAllBytes(file.toPath()), file.getName(), contentType);
        } catch (IOException e) {
            logger.error("拷贝素材异常:{}", applicationName, e);
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }
    }

    /**
     * 素材下载
     * @param mediaId 素材编号
     * @param applicationName 应用名称
     * @return 下载结果
     */
    public FileDownload download(String mediaId, String applicationName) {
        FileDownload download = null;
        if (StringUtils.isNotEmpty(mediaId)) {
            download = weChatMediaClient.download(mediaId, applicationName);
        }

        return download;
    }

    /**
     *
     * @param mediaId 素材编号
     * @param applicationName 应用名称
     * @return 下载结果
     */
    public FileDownload downloadJsMedia(String mediaId, String applicationName) {
        return weChatMediaClient.downloadJsMedia(mediaId, applicationName);
    }

    /**
     *
     * @param mediaId 素材编号
     * @param downloadFilePath 下载路径
     * @param applicationName 应用名称
     * @return 下载的文件
     */
    public File downloadJsMediaFile(String mediaId, String downloadFilePath, String applicationName) {
        return weChatMediaClient.downloadJsMediaFile(mediaId, downloadFilePath, applicationName);
    }

    /**
     * 文件下载
     *
     * @param mediaId          素材编号
     * @param downloadFilePath 下载路径
     * @param applicationName  应用名称
     * @return 下载的文件
     */
    public File downloadFile(String mediaId, String downloadFilePath, String applicationName) {
        return weChatMediaClient.downloadFile(mediaId, downloadFilePath, applicationName);
    }

    /**
     * 生成异步上传任务
     *
     * @param request          请求
     * @param applicationName  应用名称
     * @return 任务编号
     */
    public String uploadByUrl(UploadByUrlRequest request, String applicationName) {
        AsyncJobResponse response = weChatClient.uploadByUrl(request, applicationName);
        return response.getJobId();
    }

    /**
     * 查询异步任务结果
     *
     * @param request          请求
     * @param applicationName  应用名称
     * @return 任务执行结果
     */
    public UploadByUrlResultResponse getUploadByUrlResult(JobIdRequest request, String applicationName) {
        return weChatClient.getUploadByUrlResult(request, applicationName);
    }
}
