package com.github.shuaidd.client;

import com.github.shuaidd.client.annotation.Part;
import com.github.shuaidd.file.DownloadContext;
import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.file.QywxFile;
import com.github.shuaidd.response.material.UploadImageResponse;
import com.github.shuaidd.response.material.WeChatMediaUploadResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.io.File;

/**
 * enjoy your life
 *
 * @author ddshuai
 * date 2018-11-28 17:34
 **/
public interface WeChatMediaClient extends CommonClient {

    /**
     * 素材上传得到media_id，该media_id仅三天内有效
     * media_id在同一企业内应用之间可以共享
     * <p>
     * 上传临时素材
     *
     * @param media 素材
     * @param type  类型
     * @param app   应用名
     * @return WeChatMediaUploadResponse
     */
    @RequestLine("POST /media/upload?type={type}&app={app}")
    @Headers(HEAD)
    WeChatMediaUploadResponse uploadMaterial(@Part("media") QywxFile media, @Param("type") String type, @Param("app") String app);

    /**
     * 素材上传得到media_id，该media_id仅三天内有效
     * media_id在同一企业内应用之间可以共享
     * <p>
     * 上传附件
     *
     * @param media 素材
     * @param mediaType  类型
     * @param attachmentType  附件类型，不同的附件类型用于不同的场景。1：朋友圈；2:商品图册
     * @param app   应用名
     * @return WeChatMediaUploadResponse
     */
    @RequestLine("POST /media/upload_attachment?media_type={media_type}&attachment_type={attachment_type}&app={app}")
    @Headers(HEAD)
    WeChatMediaUploadResponse uploadAttachment(@Part("media") QywxFile media, @Param("media_type") String mediaType,@Param("attachment_type") Integer attachmentType, @Param("app") String app);

    /**
     * 上传图片
     *
     * @param media 素材
     * @param app   应用名
     * @return UploadImageResponse
     */
    @RequestLine("POST /media/uploadimg?app={app}")
    @Headers(HEAD)
    UploadImageResponse uploadImage(@Part("media") QywxFile media, @Param("app") String app);

    /**
     * 获取临时素材
     *
     * @param mediaId 素材编号
     * @param app     应用名
     * @return FileDownload
     */
    @RequestLine("GET /media/get?media_id={media_id}&app={app}")
    @Headers(HEAD)
    FileDownload download(@Param("media_id") String mediaId, @Param("app") String app);

    /**
     * 获取高清语音素材
     *
     * @param mediaId 通过JSSDK的uploadVoice接口上传的语音文件id
     * @param app     应用名
     * @return FileDownload
     */
    @RequestLine("GET /media/get/jssdk?media_id={media_id}&app={app}")
    @Headers(HEAD)
    FileDownload downloadJsMedia(@Param("media_id") String mediaId, @Param("app") String app);

    /**
     * 获取高清语音素材
     * @param mediaId 通过JSSDK的uploadVoice接口上传的语音文件id
     * @param downloadFilePath 下载路径
     * @param app 应用名
     * @return 下载的文件
     */
    @RequestLine("GET /media/get/jssdk?media_id={media_id}&app={app}")
    @Headers(HEAD)
    File downloadJsMediaFile(@Param("media_id") String mediaId,@Param(DownloadContext.DOWNLOAD_FILE_PATH) String downloadFilePath, @Param("app") String app);

    /**
     * 获取临时素材 下载到本地文件
     * @param mediaId 素材编号
     * @param downloadFilePath 下载的文件名
     * @param app 应用名
     * @return 下载的文件
     */
    @RequestLine("GET /media/get?media_id={media_id}&app={app}")
    @Headers(HEAD)
    File downloadFile(@Param("media_id") String mediaId,@Param(DownloadContext.DOWNLOAD_FILE_PATH) String downloadFilePath, @Param("app") String app);
}
