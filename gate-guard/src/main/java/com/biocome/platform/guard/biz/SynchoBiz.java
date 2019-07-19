package com.biocome.platform.guard.biz;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.ThreadManager;
import com.biocome.platform.guard.rpc.service.SynchoRpc;
import com.biocome.platform.guard.utils.RpcTokenUtil;
import com.biocome.platform.guard.utils.UriUtil;
import com.biocome.platform.inter.basemanager.biz.DictBiz;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Dictionary;
import com.biocome.platform.inter.basemanager.entity.Estate;
import com.biocome.platform.inter.basemanager.mapper.BuildMapper;
import com.biocome.platform.inter.basemanager.mapper.DeviceMapper;
import com.biocome.platform.inter.basemanager.mapper.EstateMapper;
import com.biocome.platform.inter.basemanager.mapper.LesseeMapper;
import com.biocome.platform.inter.basemanager.vo.common.CommonListVo;
import com.biocome.platform.inter.basemanager.vo.device.DoorDeviceVo;
import com.biocome.platform.inter.basemanager.vo.syncho.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

/**
 * @ClassName: SynchoBiz
 * @Author: shenlele
 * @Date: 2019/5/21 19:32
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SynchoBiz {

    private Logger log = LoggerFactory.getLogger(SynchoBiz.class);

    @Value("${dictCode}")
    String dictCode;
    @Autowired
    private DictBiz dictBiz;
    @Autowired
    private UriUtil uriUtil;
    @Autowired
    private SynchoRpc synchoRpc;
    @Autowired
    private BuildMapper buildMapper;
    @Autowired
    protected EstateMapper estateMapper;
    @Autowired
    private LesseeMapper lesseeMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private RpcTokenUtil rpcTokenUtil;

    /**
     * 同步小区操作
     *
     * @param vo 参数
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse<java.util.List < com.github.wxiaoqi.security.admin.vo.EstateResp>>
     * @Author shenlele
     * @Date 2019/5/21 19:36
     */
    public ObjectRestResponse estate(SynchoVo vo) throws Exception {
        List<EstateVo> list;
        Estate estate = new Estate();
        if (AdminCommonConstant.DISTRICT_BUILD.equals(vo.getType()) || AdminCommonConstant.DISTRICT_ESTATE.equals(vo.getType())) {
            list = estateMapper.selectBySynchoVo(vo);
        } else {
            if (AdminCommonConstant.DISTRICT_PROVINCE.equals(vo.getType())) {
                estate.setProvince(vo.getCode());
            } else if (AdminCommonConstant.DISTRICT_CITY.equals(vo.getType())) {
                estate.setCity(vo.getCode());
            } else if (AdminCommonConstant.DISTRICT_COUNTY.equals(vo.getType())) {
                estate.setCounty(vo.getCode());
            } else if (AdminCommonConstant.DISTRICT_STREET.equals(vo.getType())) {
                estate.setStreet(vo.getCode());
            } else if (AdminCommonConstant.DISTRICT_POLICESTATIO.equals(vo.getType())) {
                estate.setPolicestatio(vo.getCode());
            }
            list = estateMapper.selectByEstate(estate);
        }
        List<Dictionary> brands = this.selectBrand();
        for (Dictionary brand : brands) {
            ThreadManager.getInstance().execute(() -> {
                try {
                    URI uri = uriUtil.getUriByBrand(brand.getDictKey());
                    CommonListVo<EstateVo> com = new CommonListVo<>(rpcTokenUtil.getRpcToken(brand.getDictKey()), list);
                    synchoRpc.estate(uri, com);
                    log.info("同步小区信息成功！");
                } catch (Exception e) {
                    log.error("同步小区信息失败！错误信息为：" + e.getMessage());
                }
            });
        }
        return new ObjectRestResponse<>().success();
    }

    /**
     * 同步楼栋操作
     *
     * @param vo 参数
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse<java.util.List < com.github.wxiaoqi.security.admin.vo.BuildResp>>
     * @Author shenlele
     * @Date 2019/5/21 19:36
     */
    public ObjectRestResponse build(SynchoVo vo) throws Exception {
        BuildAndBrandVo build = new BuildAndBrandVo(vo);
        List<BuildVo> list = buildMapper.selectByBuild(build);
        List<Dictionary> brands = this.selectBrand();
        for (Dictionary brand : brands) {
            ThreadManager.getInstance().execute(() -> {
                try {
                    URI uri = uriUtil.getUriByBrand(brand.getDictKey());
                    CommonListVo<BuildVo> com = new CommonListVo<>(rpcTokenUtil.getRpcToken(brand.getDictKey()), list);
                    synchoRpc.build(uri, com);
                    log.info("同步楼栋信息成功！");
                } catch (Exception e) {
                    log.error("同步楼栋信息失败！错误信息为：" + e.getMessage());
                }
            });
        }
        return new ObjectRestResponse<>().success();
    }

    /**
     * 同步人员操作
     *
     * @param vo 参数
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse<java.util.List < com.github.wxiaoqi.security.admin.vo.UserResp>>
     * @Author shenlele
     * @Date 2019/5/21 19:36
     */
    public ObjectRestResponse user(SynchoVo vo) throws Exception{
        List<Dictionary> brands = this.selectBrand();
        for (Dictionary brand : brands) {
            ThreadManager.getInstance().execute(() -> {
                try {
                    BuildAndBrandVo build = new BuildAndBrandVo(vo);
                    URI uri = uriUtil.getUriByBrand(brand.getDictKey());
                    build.setBrand(brand.getDictKey());
                    List<LesseeUserVo> list = lesseeMapper.selectByVo(build);
                    CommonListVo<LesseeUserVo> com = new CommonListVo<>(rpcTokenUtil.getRpcToken(brand.getDictKey()), list);
                    synchoRpc.user(uri, com);
                    log.info("同步人员信息成功！");
                } catch (Exception e) {
                    log.error("同步人员信息失败！错误信息为：" + e.getMessage());
                }
            });
        }
        return new ObjectRestResponse<>().success();
    }

    /**
     * 同步门禁机操作
     *
     * @param vo 参数
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse<com.github.wxiaoqi.security.common.msg.auth.BaseRpcResponse>
     * @Author shenlele
     * @Date 2019/5/21 19:37
     */
    public ObjectRestResponse doorDevice(SynchoVo vo) throws Exception{
        List<Dictionary> brands = this.selectBrand();
        for (Dictionary brand : brands) {
            ThreadManager.getInstance().execute(() -> {
                try {
                    BuildAndBrandVo build = new BuildAndBrandVo(vo);
                    URI uri = uriUtil.getUriByBrand(brand.getDictKey());
                    build.setBrand(brand.getDictKey());
                    List<DoorDeviceVo> list = deviceMapper.selectByVo(build);
                    CommonListVo<DoorDeviceVo> com = new CommonListVo<>(rpcTokenUtil.getRpcToken(brand.getDictKey()), list);
                    synchoRpc.doorDevice(uri, com);
                    log.info("同步门禁机信息成功！");
                } catch (Exception e) {
                    log.error("同步门禁机信息失败！错误信息为：" + e.getMessage());
                }
            });
        }
        return new ObjectRestResponse<>().success();
    }

    /**
     * 获取字典信息（厂家信息）
     *
     * @return java.util.List<Dictionary>
     * @Author shenlele
     * @Date 2019/5/22 14:34
     */
    private List<Dictionary> selectBrand() {
        Dictionary dictionary = new Dictionary();
        dictionary.setDictCode(dictCode);
        return dictBiz.selectList(dictionary);
    }
}
