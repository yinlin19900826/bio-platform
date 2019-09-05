package com.biocome.platform.guard.biz;

import com.biocome.platform.inter.basemanager.biz.BuildBiz;
import com.biocome.platform.inter.basemanager.entity.Build;
import com.biocome.platform.guard.entity.AdminHouseBind;
import com.biocome.platform.guard.vo.admin.AdminHouseVo;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.mapper.AdminHouseBindMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员房屋授权
 *
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2019-05-15 18:33:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminHouseBindBiz extends BaseBiz<AdminHouseBindMapper, AdminHouseBind> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdminHouseBindMapper adminHouseBindMapper;
    @Autowired
    private BuildBiz buildBiz;

    /**
     * 管理员楼栋房间绑定列表
     * @param buildcode
     * @return
     */
    public TableResultResponse<AdminHouseVo> buildingHouseList(String buildcode) {
        try {
            List<AdminHouseVo> list = adminHouseBindMapper.bindBuildingHouseList(buildcode);
            return new TableResultResponse<>(list.size(), list);
        }catch (Exception e){
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查找用户授权房间失败，错误原因：数据库错误!");
        }
    }

    /***
     * 修改管理员绑定房间
     * @param buildcode 房屋编码
     * @param houseVos 要绑定的房屋列表
     * @return
     */
    public ObjectRestResponse updateHouseBind(String buildcode, List<AdminHouseVo> houseVos) {
        if(ValidateUtils.isEmpty(buildcode)){
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "楼栋编码不能为空！");
        }
        try{
            List<AdminHouseVo> addHouseVos = new ArrayList<AdminHouseVo>();
            List<AdminHouseVo> removeHouseVos = new ArrayList<AdminHouseVo>();
            for(AdminHouseVo houseVo : houseVos){
                if(houseVo.getBind() && ValidateUtils.isNotEmpty(houseVo.getId())){
                    removeHouseVos.add(houseVo);
                }else{
                    addHouseVos.add(houseVo);
                }
            }
            Build build = buildBiz.selectByBuildcode(buildcode);
            if(ValidateUtils.isEmpty(build)){
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "未知的楼栋!楼栋编码："+buildcode);
            }
            if(ValidateUtils.isNotEmpty(addHouseVos)){
                List<AdminHouseBind> bindList = new ArrayList<AdminHouseBind>();
                for(AdminHouseVo addVo : addHouseVos){
                    if(!addVo.getBind()) {
                        AdminHouseBind entity = new AdminHouseBind();
                        entity.setBuildCode(build.getBuildcode());
                        entity.setBuildName(build.getBuildname());
                        entity.setHouseCode(addVo.getHousecode());
                        entity.setHouseName(addVo.getHousename());
                        entity.setCommunityCode(build.getEstatecode());
                        entity.setCommunityName(build.getEstatename());
                        bindList.add(entity);
                    }
                }
                if(ValidateUtils.isNotEmpty(bindList)){
                    adminHouseBindMapper.batchInsert(bindList);
                }
            }
            if(ValidateUtils.isNotEmpty(removeHouseVos)){
                List<Integer> ids = new ArrayList<Integer>();
                for(AdminHouseVo removeVo : removeHouseVos){
                    if(removeVo.getBind()){
                        ids.add(removeVo.getId());
                    }
                }
                if(ValidateUtils.isNotEmpty(ids)){
                    adminHouseBindMapper.batchDeleteByIds(ids);
                }
            }
            return new ObjectRestResponse<>().success();
        }catch (Exception e){
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "下发门禁卡失败，错误信息：数据库错误！");
        }
    }

    /**
     * 绑定楼栋所有房间
     * @param buildcode
     * @return
     */
    public ObjectRestResponse<AdminHouseBind> bindAll(String buildcode) {
        try {
            adminHouseBindMapper.bindAll(buildcode);
            return new ObjectRestResponse<>().success();
        }catch (Exception e){
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "绑定所有房间失败，错误信息，数据库错误！");
        }
    }

    /**
     * 移除所有绑定
     * @param buildcode
     * @return
     */
    public ObjectRestResponse<AdminHouseBind> removeAll(String buildcode) {
        try {
            adminHouseBindMapper.removeAll(buildcode);
            return new ObjectRestResponse<>().success();
        }catch (Exception e){
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "绑定所有房间失败，错误信息，数据库错误！");
        }
    }
}