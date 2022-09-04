package com.github.shuaidd.client.decoder;

import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-06 17:37
 **/
public class WeChatMediaDecoder implements Decoder {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Decoder delegate = new CustomJacksonDecoder();
    public static final String RESPONSE_ENTITY_PACKAGE = "org.springframework.http.ResponseEntity<byte[]>";
    public static final String FILE_PACKAGE = "java.io.File";

    @Override
    public Object decode(Response response, Type type) throws FeignException, IOException {

        /*文件下载的请求 放内存*/
        if (type.getTypeName().equals(RESPONSE_ENTITY_PACKAGE)) {
            /*此处是针对小文件的下载，大型文件请自行处理*/
            byte[] body = IOUtils.toByteArray(response.body().asInputStream());
            MultiValueMap<String, String> headers = checkDownloadHeaders(response);

            if (logger.isInfoEnabled()) {
                logger.info("下载的文件大小为--{}", body.length);
            }

            return new ResponseEntity<>(body, headers, HttpStatus.OK);
        } else if (FILE_PACKAGE.equals(type.getTypeName())) {
            InputStream inputStream = IOUtils.toBufferedInputStream(response.body().asInputStream());
            MultiValueMap<String, String> headers = checkDownloadHeaders(response);
            String fileName = headers.get("content-disposition").get(0).split(";")[2].split("=")[1].replace("\"","");
            /*文件下载的请求 放本地磁盘*/
            String property = "java.io.tmpdir";
            String filePath = System.getProperty(property);

            /*去请求头内的文件名称*/
            Collection<String> headCol = response.request().headers().get(CommonClient.DOWNLOAD_FILE_PATH);
            if (headCol != null && headCol.size() > 0) {
                String path = headCol.iterator().next();
                if (StringUtils.isNotEmpty(path)) {
                    filePath = path;
                }
            }

            if (logger.isInfoEnabled()) {
                logger.info("待下载的文件路径是--{}---企业微信端文件名称-{}",filePath,fileName);
            }

            File downloadFile = new File(filePath);
            if (downloadFile.isDirectory()) {
                String realPath = Paths.get(filePath,fileName).toString();
                downloadFile = new File(realPath);
            }

            FileOutputStream outputStream = new FileOutputStream(downloadFile);
            IOUtils.copy(inputStream, outputStream);
            return downloadFile;
        } else {
            return delegate.decode(response, type);
        }
    }

    private MultiValueMap<String, String> checkDownloadHeaders(Response response) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String errorCode;
        for (String key : response.headers().keySet()) {
            headers.put(key, new LinkedList<>(response.headers().get(key)));
            if (logger.isInfoEnabled()) {
                logger.info("请求头信息--{}-->{}", key, response.headers().get(key).iterator().next());
            }

            if ("error-code".equals(key)) {
                errorCode = response.headers().get(key).iterator().next();
                String errorMsg = response.headers().get("error-msg").iterator().next();
                if (!ErrorCode.ERROR_CODE_0.getErrorCode().equals(errorCode)) {
                    logger.error("文件下载失败-->error-code-->{}-error-msg--->{}", errorCode, errorMsg);
                    throw new WeChatException(errorMsg, ErrorCode.errorCode(errorCode));
                }
            }
        }

        return headers;
    }
}
