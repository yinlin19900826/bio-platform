package com.biocome.platform.admin.biz;

import com.ace.cache.annotation.Cache;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.entity.Build;
import com.biocome.platform.admin.mapper.BuildMapper;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.common.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: BuildBiz
 * @Author: shenlele
 * @Date: 2019/5/6 19:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BuildBiz extends BaseBiz<BuildMapper, Build> {

    private Logger log = LoggerFactory.getLogger(BuildBiz.class);

    @Autowired
    private BuildMapper buildMapper;

    /**
     * 根据楼栋名称或楼栋地址查询所有楼栋信息，查询所有时传参都为null
     *
     * @param pageSize     分页数量
     * @param pageNum      分页页码
     * @param buildaddress 楼栋地址
     * @param buildname    楼栋名称
     * @return com.biocome.platform.common.msg.TableResultResponse<Build>
     * @Author shenlele
     * @Date 2019/5/8 14:08
     */
    public TableResultResponse<Build> selectByAttribute(int pageSize, int pageNum, String buildaddress, String buildname) {
        TableResultResponse<Build> res;
        try {
            Page<Build> result = PageHelper.startPage(pageNum, pageSize);
            buildMapper.selectByAttribute(buildaddress, buildname);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询楼栋信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询楼栋信息失败!");
        }
        return res;
    }

    /**
     * 根据ID删除
     *
     * @param idList 主键列表
     * @return com.biocome.platform.common.msg.ObjectRestResponse<Build>
     * @Author shenlele
     * @Date 2019/5/8 14:09
     */
    public ObjectRestResponse<Build> deleteBuild(String idList) throws Exception {
        buildMapper.deleteBuild(IdUtils.getIds(idList));
        return new ObjectRestResponse<>(true);
    }

    /**
     * 根据所传楼栋信息查询
     *
     * @param pageSize 分页数量
     * @param pageNum  分页页码
     * @param build    楼栋信息
     * @return com.biocome.platform.common.msg.TableResultResponse<Build>
     * @Author shenlele
     * @Date 2019/5/8 15:32
     */
    public TableResultResponse<Build> selectByBuild(int pageSize, int pageNum, Build build) {
        TableResultResponse<Build> res;
        try {
            //查询status为有效，del未删除的
            build.setStatus(0);
            build.setDel(0);
            Page<Build> result = PageHelper.startPage(pageNum, pageSize);
            buildMapper.select(build);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询楼栋信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询楼栋信息失败!");
        }
        return res;
    }

    @Cache(key = "buildcode:{1}")
    public Build selectByBuildcode(String buildcode) {
        Build entity = new Build();
        entity.setBuildcode(buildcode);
        return selectOne(entity);
    }

    /**
     * 插入楼栋操作（楼栋编号唯一）
     *
     * @param build 楼栋编号
     * @return com.biocome.platform.common.msg.ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/22 19:33
     */
    public ObjectRestResponse insertBuild(Build build) throws Exception {
        Build bu = new Build();
        bu.setBuildcode(build.getBuildcode());
        List<Build> builds = buildMapper.select(bu);
        if (ValidateUtils.isEmpty(builds)) {
            buildMapper.insertSelective(build);
            return new ObjectRestResponse().success();
        } else {
            throw new Exception("保存失败！楼栋编号已存在！");
        }
    }
}
