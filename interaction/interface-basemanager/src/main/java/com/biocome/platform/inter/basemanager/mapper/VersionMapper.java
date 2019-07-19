package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.entity.Version;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/2 11:49
 */
public interface VersionMapper extends Mapper<Version> {
    public Version selectByVersion(@Param("version") String version);

    public int updateAllIsalive(Version version);

    public int insertVersion(Version version);

    public int updateVersion(Version version);

    public int updateAllMilestone(Version version);

    public List<Version> getVersionList();

    public List<Version> getAliveMilestoneVersion();
}
