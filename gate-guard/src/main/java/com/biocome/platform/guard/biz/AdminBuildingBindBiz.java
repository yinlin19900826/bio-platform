package com.biocome.platform.guard.biz;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.entity.AdminBuildingBind;
import com.biocome.platform.guard.mapper.AdminBuildingBindMapper;
import com.biocome.platform.guard.vo.admin.AdminBuildingVo;
import com.biocome.platform.guard.vo.admin.AdminSummaryVo;
import com.biocome.platform.inter.basemanager.biz.BuildBiz;
import com.biocome.platform.inter.basemanager.biz.CardBiz;
import com.biocome.platform.inter.basemanager.biz.LandlordBiz;
import com.biocome.platform.inter.basemanager.constant.DefaultPhoneOpen;
import com.biocome.platform.inter.basemanager.entity.Build;
import com.biocome.platform.inter.basemanager.entity.Landlord;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 管理员楼栋授权
 *
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2019-05-15 18:33:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminBuildingBindBiz extends BaseBiz<AdminBuildingBindMapper, AdminBuildingBind> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminBuildingBindMapper adminBuildingBindMapper;
    @Autowired
    AdminCardBindBiz adminCardBindBiz;
    @Autowired
    LandlordBiz landlordBiz;
    @Autowired
    BuildBiz buildBiz;

    /***
     * 查找楼栋管理员列表
     * @param code 楼栋编码
     * @param type 管理员类型
     * @return
     */
    public TableResultResponse<AdminSummaryVo> selectAdminListOnbuilding(String code, Integer type) {
        try {
            List<AdminSummaryVo> list = adminBuildingBindMapper.selectAdminListOnBuilding(code, type);
            getAdminCardInfo(list);
            return new TableResultResponse<>(list.size(), list);
        }catch (Exception e){
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查找管理员列表失败，错误信息：数据库错误！");
        }
    }

    /**
     * 拿到管理员管理的门禁卡信息
     * @param list
     */
    private void getAdminCardInfo(List<AdminSummaryVo> list) {
        if(ValidateUtils.isNotEmpty(list)){
            List<String> userCodes = new ArrayList<String>();
            for(Iterator<AdminSummaryVo> it = list.iterator(); it.hasNext();){
                userCodes.add(it.next().getUsercode());
            }
            List<Map<String, Object>> cntListMap = adminCardBindBiz.getAdminCardCount(userCodes);
            List<Map<String, Object>> ownCardListMap = adminCardBindBiz.getAdminOwnCards(userCodes);
            if(ValidateUtils.isNotEmpty(cntListMap)){
                for(Iterator<AdminSummaryVo> it = list.iterator(); it.hasNext();){
                    AdminSummaryVo vo  = it.next();
                    for(int i = 0 ; i < cntListMap.size(); i++){
                        Map<String, Object> map = cntListMap.get(i);
                        Object usercode = map.get("usercode");
                        if(ValidateUtils.isNotEmpty(usercode)){
                           vo.setCardNum(Integer.parseInt(map.get("num").toString()));
                        }
                    }
                }
            }
            if(ValidateUtils.isNotEmpty(ownCardListMap)){
                for(Iterator<AdminSummaryVo> it = list.iterator(); it.hasNext();){
                    AdminSummaryVo vo  = it.next();
                    for(int i = 0 ; i < ownCardListMap.size(); i++){
                        Map<String, Object> map = ownCardListMap.get(i);
                        Object usercode = map.get("usercode");
                        if(ValidateUtils.isNotEmpty(usercode)){
                            vo.setCards(map.get("cards").toString());
                        }
                    }
                }
            }
        }
    }

    /***
     * 根据用户楼栋名称、社区名称查找管理员所有绑定楼栋
     *
     * @param usercode
     * @param buildName
     * @param communityName
     * @param pageNo
     * @param pageSize
     * @return
     */
    public TableResultResponse<AdminBuildingVo> adminBindBuildingList(String usercode, String buildName, String communityName, String username, String phone, int pageNo, int pageSize) {
        try {
            Page<AdminBuildingVo> result = PageHelper.startPage(pageNo, pageSize);
            adminBuildingBindMapper.adminBindBuildingList(usercode, buildName, communityName, username, phone);
            return new TableResultResponse<AdminBuildingVo>(result.getTotal(), result.getResult());
        }catch (Exception e){
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查找管理员楼栋信息失败，失败信息：数据库错误！");
        }
    }

    /***
     * 查询管理员未绑定的楼栋
     * @param usercode
     * @param buildName
     * @param communityName
     * @param pageNo
     * @param pageSize
     * @return
     */
    public TableResultResponse<AdminBuildingVo> adminBindlessBuilding(String usercode, String buildName, String communityName, int pageNo, int pageSize) {
        try {
            Page<AdminBuildingVo> result = PageHelper.startPage(pageNo, pageSize);
            adminBuildingBindMapper.selectBindlessBuilding(usercode, buildName, communityName);
            return new TableResultResponse<AdminBuildingVo>(result.getTotal(), result.getResult());
        }catch (Exception e){
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查找管理员未绑定楼栋列表失败，失败信息：数据库错误！");
        }
    }

    /***
     * 管理员绑定楼栋
     * @param usercode
     * @param buildings
     * @return
     */
    public ObjectRestResponse addBinding(String usercode, List<AdminBuildingVo> buildings) {
        try {
            Landlord landlord = landlordBiz.selectByUserCode(usercode);
            if(ValidateUtils.isEmpty(landlord)){
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "未知的管理员！");
            }
            List<AdminBuildingBind> list = new ArrayList<AdminBuildingBind>();
            for(AdminBuildingVo vo : buildings){
                AdminBuildingBind entity = new AdminBuildingBind();
                BeanUtils.copyProperties(vo, entity);
                entity.setUsercode(landlord.getUsercode());
                entity.setUsername(landlord.getUsername());
                entity.setUserType(landlord.getLandlordtype());
                entity.setPhone(landlord.getTel());
                entity.setDefaultPhoneOpen(DefaultPhoneOpen.OFF.getStatus());
                list.add(entity);
            }
            if(ValidateUtils.isNotEmpty(list)){
                adminBuildingBindMapper.batchInsert(list);
            }
            return new ObjectRestResponse<>().success();
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "授权楼栋失败，错误信息：数据库异常！");
        }
    }

    /**
     * 移除绑定
     * @param usercode
     * @param buildings
     * @return
     */
    public ObjectRestResponse removeBingding(String usercode, List<AdminBuildingVo> buildings) {
        try{
            List<Integer> ids = new ArrayList<Integer>();
            for(AdminBuildingVo vo : buildings){
                ids.add(vo.getId());
            }
            if(ValidateUtils.isNotEmpty(ids)){
                adminBuildingBindMapper.batchDeleteByIdsAndUserCode(ids, usercode);
            }
            return new ObjectRestResponse<>().success();
        }catch (Exception e){
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "取消楼栋授权失败，错误信息：数据库异常！");
        }
    }

    /**
     * 管理员绑定到制定楼栋
     * @param usercode
     * @param buildcode
     * @return
     */
    public ObjectRestResponse adminBindBuilding(String usercode, String buildcode) {
        try {
            Landlord landlord = landlordBiz.selectByUserCode(usercode);
            if(ValidateUtils.isEmpty(landlord)){
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "未知的管理员，管理员编码："+usercode);
            }
            Build build = buildBiz.selectByBuildcode(buildcode);
            if(ValidateUtils.isEmpty(build)){
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "未知的楼栋，楼栋表编码："+buildcode);
            }
            List<AdminBuildingBind> binds = mapper.selectByAdmin(usercode, buildcode);
            if(ValidateUtils.isNotEmpty(binds)){
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "管理员已绑定楼栋，不可重复绑定，管理员编码："+usercode+", 楼栋编码："+buildcode);
            }
            AdminBuildingBind entity = new AdminBuildingBind();
            entity.setUsername(landlord.getUsername());
            entity.setUsercode(landlord.getUsercode());
            entity.setUserType(landlord.getLandlordtype());
            entity.setPhone(landlord.getTel());
            entity.setDefaultPhoneOpen(DefaultPhoneOpen.OFF.getStatus());
            entity.setBuildCode(build.getBuildcode());
            entity.setBuildName(build.getBuildname());
            entity.setComunityCode(build.getEstatecode());
            entity.setComunityName(build.getEstatename());
            adminBuildingBindMapper.insertSelective(entity);
            return new ObjectRestResponse().success();
        }catch (Exception e){
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "管理员授权到制定楼栋失败，错误信息：数据库异常！");
        }
    }

}