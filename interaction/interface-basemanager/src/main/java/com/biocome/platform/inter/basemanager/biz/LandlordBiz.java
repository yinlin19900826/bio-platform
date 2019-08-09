package com.biocome.platform.inter.basemanager.biz;

import com.ace.cache.annotation.Cache;
import com.biocome.platform.inter.basemanager.entity.Landlord;
import com.biocome.platform.inter.basemanager.mapper.LandlordMapper;
/*import com.bicome.platform.inter.basemanager.vo.admin.SimpleAdminVo;*/
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.rpc.service.FileRpc;
import com.biocome.platform.inter.basemanager.vo.admin.SimpleAdminVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: LandlordBiz
 * @Author: shenlele
 * @Date: 2019/5/7 10:10
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LandlordBiz extends BaseBiz<LandlordMapper, Landlord> {

    private Logger log = LoggerFactory.getLogger(LandlordBiz.class);

    @Autowired
    private LandlordMapper landlordMapper;
    /**
     * 根据姓名，手机号，证件号码，房东类型查询，查询全部时传参为null
     *
     * @param pageSize     分页数量
     * @param pageNum      分页页码
     * @param username     姓名
     * @param tel          手机号码
     * @param papersnum    证件号码
     * @param landlordtype 房东类型
     * @return com.github.wxiaoqi.security.common.msg.TableResultResponse<Landlord>
     * @Author shenlele
     * @Date 2019/5/8 14:11
     */
    public TableResultResponse<Landlord> selectByAttribute(int pageSize, int pageNum, String username, String tel,
                                                           String papersnum, Integer landlordtype) {
        TableResultResponse<Landlord> res;
        try {
            Page<Landlord> result = PageHelper.startPage(pageNum, pageSize);
            landlordMapper.selectByAttribute(username, tel, papersnum, landlordtype);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询房东信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询房东信息失败!");
        }
        return res;
    }

    /**
     * 根据ID删除
     *
     * @param idList 主键
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse<Landlord>
     * @Author shenlele
     * @Date 2019/5/8 14:11
     */
    public ObjectRestResponse<Landlord> deleteLandlord(String idList) throws Exception {
        landlordMapper.deleteLandlord(IdUtils.getIds(idList));
        return new ObjectRestResponse<>(true);
    }

    @Cache(key = "admin:{1}")
    public Landlord selectByUserCode(String usercode) throws Exception {
        List<Landlord> list = mapper.selectByUserCode(usercode);
        if(ValidateUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 房东信息插入（房东编号唯一）
     *
     * @param landlord 房东信息
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/22 19:47
     */
    public ObjectRestResponse insertLandlord(Landlord landlord) throws Exception {
        Landlord landlord1 = new Landlord();
        landlord1.setUsercode(landlord.getUsercode());
        List<Landlord> landlords = landlordMapper.select(landlord1);
        if (ValidateUtils.isEmpty(landlords)) {
            landlordMapper.insertSelective(landlord);
            return new ObjectRestResponse().success();
        } else {
            throw new Exception("保存失败！人员编号已存在！");
        }
    }

}
