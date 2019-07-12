package com.biocome.platform.guard.biz;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.common.util.ThreadManager;
import com.biocome.platform.guard.constant.AdvertConstant;
import com.biocome.platform.guard.constant.BrandEnum;
import com.biocome.platform.guard.entity.*;
import com.biocome.platform.guard.mapper.*;
import com.biocome.platform.guard.rpc.service.AdvertRpc;
import com.biocome.platform.guard.utils.RpcTokenUtil;
import com.biocome.platform.guard.utils.UriUtil;
import com.biocome.platform.guard.vo.advert.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/31 09:55
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdvertPlanBiz extends BaseBiz<AdvertPlanMapper, AdvertPlan> {
    @Autowired
    MaterialPlanMapper materialPlanMapper;
    @Autowired
    private BuildMapper buildMapper;
    @Autowired
    private EstateMapper estateMapper;
    @Autowired
    private AdvertMaterialMapper advertMaterialMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private AdvertRpc advertRpc;
    @Autowired
    private RpcTokenUtil rpcTokenUtil;
    @Autowired
    private UriUtil uriUtil;
    @Autowired
    private AdvertListMapper advertListMapper;

    /**
     * 添加广告计划
     *
     * @param req
     * @return
     * @throws Exception
     */
    public ObjectRestResponse addAdvertplan(AddAdvertPlanReq req) throws Exception {
        //广告计划
        AdvertPlan plan = new AdvertPlan();

        plan.setPlanname(req.getPlanname());
        plan.setDownestate(JsonUtils.beanListToJson(req.getCodes()));
        plan.setIsaudit(AdvertConstant.ADVERT_PLAN_ISAUDIT_AUDITING);
        plan.setCreatename(BaseContextHandler.getUsername());
        plan.setCreatetime(DateUtils.getCurrentDate());
        plan.setCreatecode(BaseContextHandler.getUserID());
        plan.setBegintime(req.getBegintime());
        plan.setEndtime(req.getEndtime());
        plan.setSpeed(req.getSpeed());
        plan.setPriority(req.getPriority());
        int result = mapper.insertReturnId(plan);

        //广告计划绑定素材
        List<MaterialPlan> list = new ArrayList<>();
        for (Integer id : req.getMaterialIds()) {
            MaterialPlan materialPlan = new MaterialPlan();
            materialPlan.setMaterialid(id);
            materialPlan.setPlanid(plan.getId());
            materialPlan.setUpdatetime(DateUtils.getCurrentDate());
            materialPlan.setStatus(AdvertConstant.MATERIALPLAN_STATUS_ACTIVE);
            materialPlan.setCreatecode(BaseContextHandler.getUsername());
            materialPlan.setCreatetime(DateUtils.getCurrentDate());
            materialPlan.setCreatecode(BaseContextHandler.getUserID());
            list.add(materialPlan);
        }
        int materialPlanResult = materialPlanMapper.insertListMaterialPlan(list);

        if (result > 0 && materialPlanResult > 0) {
            return new ObjectRestResponse().success();
        } else {
            throw new Exception("广告计划存库时失败");
        }
    }

    /**
     * 获取广告计划列表
     *
     * @param id
     * @param planname
     * @param isaudit
     * @param starttime
     * @param endtime
     * @return
     * @throws Exception
     */
    public List<AdvertPlan> getAdvertPlanList(Integer id,
                                              String planname,
                                              String isaudit,
                                              Date starttime,
                                              Date endtime) throws Exception {
        return mapper.getAdvertPlanList(id, planname, isaudit, starttime, endtime);
    }

    /**
     * 批量删除广告计划
     *
     * @param advertPlanIds
     * @return
     * @throws Exception
     */
    public int deleteAdvertPlan(List<Integer> advertPlanIds) throws Exception {
        materialPlanMapper.deleteByAdvertPlanIds(advertPlanIds);
        return mapper.deleteAdvertPlan(advertPlanIds);
    }

    /**
     * 预览广告计划
     *
     * @param id
     * @return
     */
    public AdvertPlanPreviewResp getPreviewAdvertPlan(Integer id) throws Exception {
        AdvertPlanPreviewResp resp = new AdvertPlanPreviewResp();

        AdvertPlan advertPlan = mapper.selectByPrimaryKey(id);
//        List<Estate> estateList = new ArrayList<>();
//        List<Build> buildList = new ArrayList<>();
        if (advertPlan != null) {
            //根据计划id查询所有素材
            List<AdvertPreviewResp> advertPreviewResps = advertMaterialMapper.selectAdvertPreviewRespByPlanId(id);
            resp.setAdvertPreviewRespList(advertPreviewResps);
            String downestate = advertPlan.getDownestate();
            List<String> codes = JsonUtils.jsonToList(downestate);
            //根据codes获取楼栋列表和社区列表
            codeHandler(codes, resp);
        }
        return resp;
    }

    /**
     * 楼栋编码获取楼栋名称
     *
     * @param codes
     * @param resp
     */
    public void codeHandler(List<String> codes, AdvertPlanPreviewResp resp) {
        List<String> estatecodeList = new ArrayList<>();
        List<String> buildcodeList = new ArrayList<>();
        List<String> needBuildcodeList = new ArrayList<>();
        for (String code : codes) {
            if (code.length() <= AdvertConstant.OTHERCODE_LENTH) {
                //过滤掉社区以上层级
                continue;
            } else if (code.length() <= AdvertConstant.ESTATECODE_LENTH) {
                //社区
                estatecodeList.add(code);
            } else {
                //楼栋
                buildcodeList.add(code);
            }
        }
        for (String code : buildcodeList) {
            //社区包含有相应楼栋的，就去掉不显示
            StringBuilder sb = new StringBuilder();
            sb.append(code);
            //获取社区编号
            String estatecode = sb.substring(0, 16);
            if (!estatecodeList.contains(estatecode)) {
                //如果社区列表里面没有此社区编号说明楼栋要显示
                needBuildcodeList.add(code);
            }
        }
        //设置为空，方便回收
        buildcodeList = null;
        //查询所有社区信息
        if (estatecodeList.size() > 0) {
            resp.setEstateList(estateMapper.queryEstateByEstatecode(estatecodeList));
        }
        //查询所有楼栋信息
        if (needBuildcodeList.size() > 0) {
            resp.setBuildList(buildMapper.queryBuildByBuildcode(needBuildcodeList));
        }
    }

    /**
     * 下发广告
     *
     * @param id
     */
    public void rpcAdvert(Integer id) throws Exception {
        //获取广告计划
          AdvertPlan advertPlan = mapper.selectByPrimaryKey(id);
        //获取该广告计划下所有素材
        List<AdvertMaterial> materials = advertMaterialMapper.selectMaterialByPlanId(id);
        //下发广告的实体类
        AddAdvertList addAdvertList = new AddAdvertList();

        List<AdvertDetail> advertDetaillist = new ArrayList<>();

        for (AdvertMaterial advertMaterial : materials) {
            AdvertDetail advertDetail = new AdvertDetail();
            //广告编号，为广告计划ID
            advertDetail.setAdno(advertPlan.getId().toString());
            //广告名称，素材名称
            advertDetail.setAdname(advertMaterial.getMaterialname());
            //素材类型
            advertDetail.setType(advertMaterial.getType());
            //素材路径
            advertDetail.setFilepath(advertMaterial.getFilepath());
            //播放开始时间
            advertDetail.setBegintime(advertPlan.getBegintime());
            //播放结束时间
            advertDetail.setEndtime(advertPlan.getEndtime());
            //播放速度
            advertDetail.setSpeed(advertPlan.getSpeed());
            //播放优先级
            advertDetail.setPriority(advertPlan.getPriority());
            //素材ID
            advertDetail.setFileid(advertMaterial.getId().toString());

            advertDetaillist.add(advertDetail);
        }

        addAdvertList.setList(advertDetaillist);
        //取得下发社区
        String downestate = advertPlan.getDownestate();
        List<String> codes = JsonUtils.jsonToList(downestate);
        List<String> buildcodeList = new ArrayList<>();

        for (String code : codes) {
            //获取所有楼栋编号
            if (code.length() == AdvertConstant.BUILDCODE_LENTH) {
                buildcodeList.add(code);
            }
        }
        //根据楼栋编号获取门禁机的sn和brand信息
        List<Device> devices = deviceMapper.selectSnAndCardByBuildCodes(buildcodeList);
        //根据品牌编号确定分X组,放入X个队列
        Integer brandNum = BrandEnum.getBrandNum();
        List<List<String>> strLists = new ArrayList<>();
        for (int i = 1; i <= brandNum; i++) {
            List<String> list = new ArrayList<>();
            strLists.add(list);
        }
        //根据品牌，对Sn分组
        for (Device device : devices) {
            strLists.get(Integer.valueOf(device.getBrand()) - 1).add(device.getSn());
        }
        //批量下发到各个小平台
        for (int i = 1; i <= brandNum; i++) {
            final int j = i;
            ThreadManager.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    if (strLists.get(j - 1) != null && strLists.get(j - 1).size() > 0) {
                        List<AdvertSnList> advertSnList = new ArrayList<>();

                        List<Advert> advertList = new ArrayList<>();
                        for (String sn : strLists.get(j - 1)) {
                            AdvertSnList snList = new AdvertSnList(sn);

                            advertSnList.add(snList);
                            for (AdvertMaterial advertMaterial : materials) {
                                Advert advert = new Advert();
                                advert.setAdno(advertPlan.getId().toString());
                                advert.setSn(sn);
                                advert.setAdname(advertPlan.getPlanname());
                                advert.setMaterialname(advertMaterial.getMaterialname());
                                advert.setType(advertMaterial.getType());
                                advert.setFilepath(advertMaterial.getFilepath());
                                advert.setBegintime(advertPlan.getBegintime());
                                advert.setEndtime(advertPlan.getEndtime());
                                advert.setSpeed(advertPlan.getSpeed());
                                advert.setPriority(advertPlan.getPriority());
                                advert.setFileid(advertMaterial.getId().toString());
                                advert.setCreatetime(DateUtils.getCurrentTime());
                                advertList.add(advert);
                            }
                        }
                        //批量插入数据
                        advertListMapper.insertList(advertList);
                        String token = rpcTokenUtil.getRpcToken(Integer.toString(j));
                        addAdvertList.setToken(token);
                        addAdvertList.setListsn(advertSnList);
                        URI uri = uriUtil.getUriByBrand(Integer.toString(j));
                        List<AdvertAddListRpcResp> advertAddListRpcResps = null;
                        try {
                            advertAddListRpcResps = advertRpc.advertList(uri, addAdvertList);
                            log.info("批量下发广告的响应结果为:{}",advertAddListRpcResps.toString());
                        }catch (Exception e){
                            log.info(e.getMessage());
                            System.out.println(JsonUtils.beanToJson(addAdvertList));
                            e.printStackTrace();
                        }
                        if (advertAddListRpcResps != null && advertAddListRpcResps.size() > 0) {
                            advertListMapper.updateList(advertAddListRpcResps);
                        } else if (advertAddListRpcResps == null) {
                            advertAddListRpcResps = new ArrayList<>();
                            for (String sn : strLists.get(j - 1)) {
                                AdvertAddListRpcResp resp = new AdvertAddListRpcResp();
                                resp.setAdno(advertPlan.getId().toString());
                                resp.setSn(sn);
                                resp.setResult("0");
                                advertAddListRpcResps.add(resp);
                            }
                            advertListMapper.updateList(advertAddListRpcResps);
                        }
                    }
                }
            });
        }
    }
}
