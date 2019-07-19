package com.biocome.platform.inter.basemanager.utils;

import com.biocome.platform.inter.basemanager.vo.upload.FileVo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @date 2019/7/19 10:48
 */
public class FileUtils {
    public static List<FileVo> getFileDetailByUrls(String type, String... urls) {
        List<FileVo> fileVos = new ArrayList<>();
        for (String url : urls) {
            String[] details = url.split("/");
            FileVo vo = new FileVo();
            vo.setType(type);
            vo.setTopic(details[3]);
            vo.setFilename(details[4]);
            fileVos.add(vo);
        }
        return fileVos;
    }
}
