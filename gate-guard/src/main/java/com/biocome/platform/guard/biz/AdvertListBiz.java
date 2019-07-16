package com.biocome.platform.guard.biz;

import com.alibaba.fastjson.JSON;
import com.biocome.platform.guard.constant.AdminCommonConstant;
import com.biocome.platform.guard.entity.Advert;
import com.biocome.platform.guard.rpc.service.AdvertRpc;
import com.biocome.platform.guard.utils.RpcTokenUtil;
import com.biocome.platform.guard.utils.UriUtil;
import com.biocome.platform.guard.vo.advert.*;
import com.biocome.platform.guard.vo.device.DeviceSnVo;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.mapper.AdvertListMapper;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.mapper.DeviceMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AdvertListBiz
 * @Author: shenlele
 * @Date: 2019/6/3 15:05
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdvertListBiz extends BaseBiz<AdvertListMapper, Advert> {

    @Autowired
    private AdvertRpc rpc;
    @Autowired
    private UriUtil uriUtil;
    @Autowired
    private RpcTokenUtil rpcTokenUtil;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private AdvertListMapper advertMapper;

    /**
     * 根据所传参数分页查询广告列表
     *
     * @param pageSize 每页条数
     * @param pageNum  页码
     * @param advert   参数
     * @return TableResultResponse<Advert>
     * @Author shenlele
     * @Date 2019/6/3 18:00
     */
    public TableResultResponse<Advert> selectByAttribute(int pageSize, int pageNum, AdvertVo advert) throws Exception {
        Page<Advert> result = PageHelper.startPage(pageNum, pageSize);
        advertMapper.selectByAttribute(advert);
        return new TableResultResponse<>(result.getTotal(), result.getResult());
    }

    /**
     * 根据ID和SN查询删除广告（含远程删除）
     *
     * @param id 主键
     * @param sn 设备编号
     * @return ObjectRestResponse
     * @Author shenlele
     * @Date 2019/6/3 18:01
     */
    public ObjectRestResponse remove(Integer id, String sn) throws Exception {
        Advert advert = advertMapper.selectByPrimaryKey(id);
        if (ValidateUtils.isNotEmpty(advert)) {
            Device model = new Device();
            model.setSn(sn);
            List<Device> devices = deviceMapper.select(model);
            if (ValidateUtils.isNotEmpty(devices)) {
                String brand = devices.get(0).getBrand();
                if (ValidateUtils.isNotEmpty(brand)) {
                    //组装数据，远程删除设备广告
                    RemoveDetail removeDetail = new RemoveDetail();
                    BeanUtils.copyProperties(advert, removeDetail);
                    List<RemoveDetail> removeDetails = new ArrayList<>();
                    removeDetails.add(removeDetail);
                    List<AdvertSnList> listSn = new ArrayList<>();
                    AdvertSnList snList = new AdvertSnList(sn);
                    listSn.add(snList);
                    URI uri = uriUtil.getUriByBrand(brand);
                    RemoveAdvertList removeAdvertList = new RemoveAdvertList(rpcTokenUtil.getRpcToken(brand), removeDetails, listSn);
                    List<AdvertAddListRpcResp> resps = rpc.removeadlist(uri, removeAdvertList);
                    if (ValidateUtils.isNotEmpty(resps)) {
                        for (AdvertAddListRpcResp resp : resps) {
                            if (AdminCommonConstant.DEFAULT_ONE.equals(resp.getResult()) && ValidateUtils.isNotEmpty(resp.getAdno())) {
                                //根据ID删除数据库该广告信息
                                advertMapper.deleteByParam(resp.getAdno(), sn);
                            } else {
                                throw new Exception("移除广告失败，远程调用移除失败，返回值为" + JSON.toJSONString(resp));
                            }
                        }
                        return new ObjectRestResponse().success();
                    } else {
                        throw new Exception("移除广告失败，远程调用无返回值，参数SN为：" + sn);
                    }
                } else {
                    throw new Exception("移除广告失败，设备信息无厂家信息，参数SN为：" + sn);
                }
            } else {
                throw new Exception("移除广告失败，无该设备信息，参数SN为：" + sn);
            }
        } else {
            throw new Exception("移除广告失败，无该广告信息，参数ID为：" + id);
        }
    }

    /**
     * 根据SN清空广告信息（含远程清空设备广告）
     *
     * @param sn 设备编号
     * @return ObjectRestResponse
     * @Author shenlele
     * @Date 2019/6/3 18:21
     */
    public ObjectRestResponse clearAd(String sn) throws Exception {
        Device model = new Device();
        model.setSn(sn);
        List<Device> devices = deviceMapper.select(model);
        if (ValidateUtils.isNotEmpty(devices)) {
            String brand = devices.get(0).getBrand();
            if (ValidateUtils.isNotEmpty(brand)) {
                URI uri = uriUtil.getUriByBrand(brand);
                DeviceSnVo vo = new DeviceSnVo(rpcTokenUtil.getRpcToken(brand), sn);
                BaseRpcResponse resp = rpc.clearAdvert(uri, vo);
                if (ValidateUtils.isNotEmpty(resp) && AdminCommonConstant.DEFAULT_ONE.equals(resp.getResult())) {
                    advertMapper.deleteByParam(null, sn);
                    return new ObjectRestResponse().success();
                } else {
                    throw new Exception("清空广告失败，远程调用失败，参数SN为：" + sn);
                }
            } else {
                throw new Exception("清空广告失败，设备信息无厂家信息，参数SN为：" + sn);
            }
        } else {
            throw new Exception("清空广告失败，无该设备信息，参数SN为：" + sn);
        }
    }
}
