package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.rpc.service.FileRpc;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.inter.basemanager.entity.Version;
import com.biocome.platform.inter.basemanager.mapper.VersionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/2 13:59
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VersionBiz extends BaseBiz<VersionMapper, Version> {
    @Autowired
    FileRpc fileRpc;

    public Version selectByVersion(String version) {
        return mapper.selectByVersion(version);
    }

    public int updateAllIsalive(Version version) {
        return mapper.updateAllIsalive(version);
    }

    public int updateVersion(Version version) {
        return mapper.updateVersion(version);
    }

    public int insertVersion(Version version) {
        return mapper.insertVersion(version);
    }

    public int updateAllMilestone(Version version) {
        return mapper.updateAllMilestone(version);
    }

    public void uploadClient(MultipartFile object, String filename, String version) throws Exception {
        ObjectRestResponse objectRestResponse = fileRpc.fileUpload(object, "4");
        String str = objectRestResponse.getData().toString();
                //存库
        Version result = selectByVersion(version);
        Version v = new Version();
        v.setFilepath(str);
        v.setVersion(version);
        v.setCreatetime(DateUtils.getCurrentTime());
        v.setFilename(filename);

        if (result == null) {
            //获取版本号所对应的的版本类型
            int versionType = versionHandle(version);
            v.setIsalive(Integer.toString(versionType));
            v.setDeadtime(DateUtils.getCurrentTime());
            if (versionType == 2) {
                //如果是里程碑版本
                updateAllMilestone(v);
                updateAllIsalive(v);
            } else {
                //如果是普通版本
                updateAllIsalive(v);
            }
            v.setDeadtime(null);
            insertVersion(v);
        } else {
            v.setId(result.getId());
            v.setIsalive(result.getIsalive());
            updateVersion(v);
        }
    }

    public int versionHandle(String version) {
        //1为有效版本，2为里程碑版本
        int i = 0;
        String[] strings = version.split("\\.");

        for (int j = 0; j < strings.length; j++) {
            if (j == strings.length - 1 && Integer.valueOf(strings[j]) == 0) {
                i = 2;
            } else {
                i = 1;
            }
        }
        return i;
    }

    public String verifyVersion(String version) {
        final String result = "0";
        if (version.startsWith("V") || version.startsWith("v")) {
            version = version.substring(1);
        }
        String[] strings = version.split("\\.");
        try {
            Integer.valueOf(strings[0]);
            Integer.valueOf(strings[strings.length - 1]);
        } catch (NumberFormatException nfe) {
            return result;
        }
        List<Version> versionList = mapper.getVersionList();
        if (!compareVersion(version, versionList)) {
            return result;
        }

        return version;
    }

    private boolean compareVersion(String version, List<Version> versionList) {
        boolean pass = true;
        boolean nopass = false;

        String milestoneVersion = null;
        String aliveVersion = null;
        String[] milestoneVersions = null;
        String[] aliveVersions = null;

        if (versionList.size() > 0) {
            for (Version version1 : versionList) {
                if (version1.getIsalive().equals("2")) {
                    //里程碑版本
                    milestoneVersion = version1.getVersion();
                    milestoneVersions = milestoneVersion.split("\\.");
                } else if (version1.getIsalive().equals("1")) {
                    //活跃版本
                    aliveVersion = version1.getVersion();
                    aliveVersions = aliveVersion.split("\\.");
                }
            }
        }
        String[] versions = version.split("\\.");


        if (StringUtils.isBlank(milestoneVersion) && StringUtils.isBlank(aliveVersion)) {
            //若有效里程碑版本和有效版本都没有，则为首次上传文件包
            if (Integer.valueOf(versions[versions.length - 1]) == 0) {
                //如果首次上传包为里程碑版本，则通过。否则不通过
                return pass;
            } else {
                return nopass;
            }
        } else if (StringUtils.isBlank(milestoneVersion) && StringUtils.isNotBlank(aliveVersion)) {
            //若有效里程碑版本没有和有效版本有，则版本管理有异常，不通过
            return nopass;
        } else if (StringUtils.isNotBlank(milestoneVersion) && StringUtils.isBlank(aliveVersion)) {
            //若只有里程碑版本，则需要对比
            if (Integer.valueOf(versions[0]) < Integer.valueOf(milestoneVersions[0])) {
                //当大版本号小于里程碑版本的大版本号，直接返回版本错误
                return nopass;
            } else if (Integer.valueOf(versions[0]).equals(Integer.valueOf(milestoneVersions[0]))) {
                //当大版本号等于里程碑版本的大版本号
                if (Integer.valueOf(versions[versions.length - 1]) == 0) {
                    //如果上传的是里程碑版本，返回版本错误
                    return nopass;
                } else {
                    return pass;
                }
            } else {
                //当大版本号大于里程碑版本的大版本号
                if (Integer.valueOf(versions[versions.length - 1]) != 0) {
                    //如果上传的不是里程碑版本，返回版本错误
                    return nopass;
                } else {
                    return pass;
                }
            }
        } else {
            //若里程碑版本和有效版本都有
            if (Integer.valueOf(versions[0]) < Integer.valueOf(milestoneVersions[0])) {
                //当大版本号小于里程碑版本的大版本号，直接返回版本错误
                return nopass;
            } else if (Integer.valueOf(versions[0]).equals(Integer.valueOf(milestoneVersions[0]))) {
                //当大版本号等于于里程碑版本的大版本号
                if (Integer.valueOf(versions[versions.length - 1])
                        <= Integer.valueOf(aliveVersions[aliveVersions.length - 1])) {
                    //当小版本号小于活跃版本的小版本号，返回版本错误
                    return nopass;
                } else {
                    return pass;
                }
            } else {
                //当大版本号大于于里程碑版本的大版本号
                if (Integer.valueOf(versions[versions.length - 1]) != 0) {
                    //如果上传的不是里程碑版本，返回版本错误
                    return nopass;
                } else {
                    return pass;
                }
            }
        }


    }

    public List<Version> getVersionList() {
        return mapper.getVersionList();
    }

}
