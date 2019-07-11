package com.biocome.platform.admin.utils;

import com.github.tobato.fastdfs.domain.proto.mapper.DynamicFieldType;
import com.github.tobato.fastdfs.domain.proto.mapper.FdfsColumn;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import org.apache.commons.lang3.Validate;

/**
 * @ClassName: AnalysisPath
 * @Author: shenlele
 * @Date: 2019/5/28 18:33
 * @Description:
 */
public class AnalysisPath {
    @FdfsColumn(
            index = 0,
            max = 16
    )
    private String group;
    @FdfsColumn(
            index = 1,
            dynamicField = DynamicFieldType.allRestByte
    )
    private String path;
    private static final String SPLIT_GROUP_NAME = "biocome";

    public AnalysisPath() {
    }

    public AnalysisPath(String group, String path) {
        this.group = group;
        this.path = path;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullPath() {
        return this.group.concat("/").concat(this.path);
    }

    @Override
    public String toString() {
        return "StorePath [group=" + this.group + ", path=" + this.path + "]";
    }

    public static AnalysisPath parseFromUrl(String filePath) {
        Validate.notNull(filePath, "解析文件路径不能为空", new Object[0]);
        String group = getGroupName(filePath);
        int pathStartPos = filePath.indexOf(group) + group.length() + 1;
        String path = filePath.substring(pathStartPos);
        return new AnalysisPath(group, path);
    }

    private static String getGroupName(String filePath) {
        String[] paths = filePath.split("/");
        if (paths.length == 1) {
            throw new FdfsUnsupportStorePathException("解析文件路径错误,有效的路径样式为(group/path) 而当前解析路径为".concat(filePath));
        } else {
            System.out.println(paths);
            String[] var2 = paths;
            int var3 = paths.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String item = var2[var4];
                if (item.contains(SPLIT_GROUP_NAME)) {
                    return item;
                }
            }

            throw new FdfsUnsupportStorePathException("解析文件路径错误,被解析路径url没有group,当前解析路径为".concat(filePath));
        }
    }
}
