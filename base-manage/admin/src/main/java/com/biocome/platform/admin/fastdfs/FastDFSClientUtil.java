package com.biocome.platform.admin.fastdfs;

import com.biocome.platform.common.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @ClassName: FastDFSClientUtil
 * @Author: shenlele
 * @Date: 2019/5/16 16:57
 * @Description:
 */
@Component
public class FastDFSClientUtil {
    @Autowired
    private FastDfsClientWrapper fastDfsClientWrapper;

    private final Logger logger = LoggerFactory.getLogger(FastDFSClientUtil.class);

    /**
     * 文件上传
     * 最后返回fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     *
     * @param file 文件
     * @return java.lang.String
     * @Author shenlele
     * @Date 2019/5/16 16:59
     */
    public String uploadFile(MultipartFile file) throws Exception {
        byte[] bytes = file.getBytes();
        //获取源文件名称
        String originalFileName = file.getOriginalFilename();
        //获取文件后缀--.doc
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        //获取文件大小
        long fileSize = file.getSize();
        return fastDfsClientWrapper.uploadFile(bytes, fileSize, extension);
    }

    /**
     * 文件下载
     *
     * @param fileUrl  文件地址
     * @param response 作用域
     * @return void
     * @Author shenlele
     * @Date 2019/5/16 16:59
     */
    public void downloadFile(String fileUrl, HttpServletResponse response) throws Exception {
        byte[] bytes = fastDfsClientWrapper.downloadFile(fileUrl);
        // 这里只是为了整合fastdfs，所以写死了文件格式。需要在上传的时候保存文件名。下载的时候使用对应的格式
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("sb.xlsx", "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            logger.error("文件下载失败，错误信息为：" + e.getMessage());
            throw new IOException(e);
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件地址
     * @return void
     * @Author shenlele
     * @Date 2019/5/28 18:14
     */
    public void deleteFile(String fileUrl) throws Exception {
        if (ValidateUtils.isNotEmpty(fileUrl)) {
            fastDfsClientWrapper.deleteFile(fileUrl);
        } else {
            return;
        }
    }
}
