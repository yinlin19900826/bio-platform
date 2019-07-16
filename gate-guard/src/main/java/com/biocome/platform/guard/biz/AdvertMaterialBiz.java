package com.biocome.platform.guard.biz;

import com.biocome.platform.guard.constant.AdminCommonConstant;
import com.biocome.platform.guard.entity.AdvertMaterial;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.mapper.AdvertMaterialMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * @ClassName: AdvertMaterialBiz
 * @Author: shenlele
 * @Date: 2019/5/30 16:21
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdvertMaterialBiz extends BaseBiz<AdvertMaterialMapper, AdvertMaterial> {

    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private AdvertMaterialMapper mapper;

    /**
     * 根据所传参数查询素材列表
     *
     * @param pageSize 分页数量
     * @param pageNum  分页页码
     * @param model    所传参数
     * @return TableResultResponse<AdvertMaterial>
     * @Author shenlele
     * @Date 2019/5/30 16:44
     */
    public TableResultResponse<AdvertMaterial> selectByAttribute(int pageSize, int pageNum, AdvertMaterial model) throws Exception {
        Page<AdvertMaterial> result = PageHelper.startPage(pageNum, pageSize);
        mapper.selectByAttribute(model);
        return new TableResultResponse(result.getTotal(), result.getResult());
    }

    /**
     * 根据所传idList批量删除数据
     *
     * @param idList id列表
     * @return ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/30 16:56
     */
    public ObjectRestResponse deleteByIdList(String idList) throws Exception {
        List<Integer> list = IdUtils.getIds(idList);
        try {
            List<AdvertMaterial> advertMaterials = mapper.selectByList(list);
            mapper.deleteByIdList(list);
            for (AdvertMaterial model : advertMaterials) {
                if (!AdminCommonConstant.DEFAULT_TWO.equals(model.getType()) && ValidateUtils.isNotEmpty(model.getFilepath())) {
                    //删除素材后，将已上传文件放入redis待删除区
                    jedisCluster.lpush(AdminCommonConstant.DELETE_KEY, model.getFilepath());
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ObjectRestResponse().success();
    }

    /**
     * 插入广告素材
     *
     * @param model 广告素材
     * @return ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/30 17:50
     */
    public ObjectRestResponse insertMaterial(AdvertMaterial model) throws Exception {
        ObjectRestResponse res;
        //先查询广告名称是否存在
        AdvertMaterial advertMaterial = new AdvertMaterial();
        advertMaterial.setMaterialname(model.getMaterialname());
        List<AdvertMaterial> list = mapper.select(advertMaterial);
        if (ValidateUtils.isEmpty(list)) {
            if (AdminCommonConstant.DEFAULT_TWO.equals(model.getType())) {
                model.setFilepath(model.getMaterialname());
            }
            try {
                mapper.insertSelective(model);
            } catch (Exception e) {
                //插入失败后，将已上传文件放入redis待删除区
                jedisCluster.lpush(AdminCommonConstant.DELETE_KEY, model.getFilepath());
                throw new Exception(e);
            }
            res = new ObjectRestResponse().success();
        } else {
            res = new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "广告素材名称已存在！");
        }
        return res;
    }
}
