package com.github.shuaidd.service;

import com.github.shuaidd.response.UploadImageResponse;
import com.github.shuaidd.response.WeChatMediaUploadResponse;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * 描述
 * <p>
 *
 * @author ddshuai
 * date 2019-04-06 16:18
 **/
@Service
public class MediaService extends AbstractBaseService {

    public static final String FILE = "file";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";

    /**
     * 上传临时素材
     *
     * @param file
     * @param mediaType
     * @param type
     * @param applicationName
     * @return
     */
    public final String uploadMaterial(File file, MediaType mediaType, String type, String applicationName) {
        checkApplication(applicationName);
        String mediaId = null;
        if (Objects.nonNull(file) && Objects.nonNull(mediaType)) {
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    mediaType.getType(), true, file.getName());

            try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            } catch (Exception e) {
                logger.error("拷贝素材异常:{}", applicationName, e);
                throw new IllegalArgumentException("Invalid file: " + e, e);
            }

            MultipartFile multi = new CommonsMultipartFile(fileItem);
            mediaId = uploadMaterial(multi, type, applicationName);
        }

        return mediaId;
    }

    public final String uploadMaterial(MultipartFile file, String type, String applicationName) {
        checkApplication(applicationName);
        String mediaId = null;
        if (Objects.nonNull(file) && Objects.nonNull(type)) {
            WeChatMediaUploadResponse response = weChatMediaClient.uploadMaterial(file, type, applicationName);
            if (isSuccess(response)) {
                mediaId = response.getMediaId();
            }
        }

        return mediaId;
    }

    /**
     * 上传永久图片
     *
     * @param file
     * @param mediaType
     * @param applicationName
     * @return
     */
    public final String uploadImage(File file, MediaType mediaType, String applicationName) {
        checkApplication(applicationName);
        String url = null;
        if (Objects.nonNull(file) && Objects.nonNull(mediaType)) {
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    mediaType.getType(), true, file.getName());

            try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            } catch (Exception e) {
                logger.error("拷贝素材异常:{}", applicationName, e);
                throw new IllegalArgumentException("Invalid file: " + e, e);
            }

            MultipartFile multi = new CommonsMultipartFile(fileItem);
            UploadImageResponse response = weChatMediaClient.uploadImage(multi, applicationName);
            if (isSuccess(response)) {
                url = response.getUrl();
            }
        }

        return url;
    }

    public final ResponseEntity<byte[]> download(String mediaId, String applicationName) {
        ResponseEntity<byte[]> responseEntity = null;
        if (StringUtils.isNotEmpty(mediaId)) {
            responseEntity = weChatMediaClient.download(mediaId, applicationName);
        }

        return responseEntity;
    }
}
