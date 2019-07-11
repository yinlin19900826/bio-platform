package com.biocome.platform.admin.fastdfs;

import com.biocome.platform.admin.utils.AnalysisPath;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @ClassName: FastDfsClientWrapper
 * @Author: shenlele
 * @Date: 2019/5/16 16:55
 * @Description:
 */
@Component
public class FastDfsClientWrapper {
    private final Logger log = LoggerFactory.getLogger(FastDfsClientWrapper.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Value("${img-readpath}")
    private String hostPath;

    /**
     * 文件上传
     *
     * @param bytes     文件
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return java.lang.String 返回fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     * @Author shenlele
     * @Date 2019/5/16 17:42
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
        log.info(storePath.getGroup() + "==" + storePath.getPath() + "======" + storePath.getFullPath());
        return getResAccessUrl(storePath);
    }

    /**
     * 下载文件
     * 返回文件字节流大小
     *
     * @param fileUrl 文件URL
     * @return byte[] 文件字节
     * @Author shenlele
     * @Date 2019/5/16 17:43
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }

    /**
     * 封装文件完整URL地址
     *
     * @param storePath
     * @return java.lang.String
     * @Author shenlele
     * @Date 2019/5/16 17:01
     */
    private String getResAccessUrl(StorePath storePath) {
        return hostPath + storePath.getFullPath();
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件地址
     * @return void
     * @Author shenlele
     * @Date 2019/5/28 18:10
     */
    public void deleteFile(String fileUrl) throws Exception{
        AnalysisPath analysisPath = AnalysisPath.parseFromUrl(fileUrl);
        fastFileStorageClient.deleteFile(analysisPath.getGroup(), analysisPath.getPath());
    }
}
