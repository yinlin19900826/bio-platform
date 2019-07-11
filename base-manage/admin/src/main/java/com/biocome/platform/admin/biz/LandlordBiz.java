package com.biocome.platform.admin.biz;

import com.ace.cache.annotation.Cache;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.entity.Landlord;
import com.biocome.platform.admin.fastdfs.FastDFSClientUtil;
import com.biocome.platform.admin.mapper.LandlordMapper;
import com.biocome.platform.admin.vo.admin.SimpleAdminVo;
import com.biocome.platform.admin.vo.upload.ChangeLesseePicReq;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.common.util.ValidateUtils;
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
    @Autowired
    FastDFSClientUtil fastDFSClientUtil;

    /**
     * 根据姓名，手机号，证件号码，房东类型查询，查询全部时传参为null
     *
     * @param pageSize     分页数量
     * @param pageNum      分页页码
     * @param username     姓名
     * @param tel          手机号码
     * @param papersnum    证件号码
     * @param landlordtype 房东类型
     * @return com.biocome.platform.common.msg.TableResultResponse<Landlord>
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
     * @return com.biocome.platform.common.msg.ObjectRestResponse<Landlord>
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
     * @return com.biocome.platform.common.msg.ObjectRestResponse
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

    /**
     * 查找管理员信息
     * @param username
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    public TableResultResponse<SimpleAdminVo> simpleAdminList(String username, Integer type, int pageNum, int pageSize) {
        try {
            Page<SimpleAdminVo> result = PageHelper.startPage(pageNum, pageSize);
            landlordMapper.selectSimpleAdminList(username, type);
            return new TableResultResponse<SimpleAdminVo>(result.getTotal(), result.getResult());
        }catch (Exception e){
            log.info(e.getMessage());
            e.printStackTrace();
            return new TableResultResponse<SimpleAdminVo>(CommonConstants.EX_OTHER_CODE, "查找管理员列表失败，错误信息：数据库错误!");
        }
    }

    public BaseRpcResponse changePic(ChangeLesseePicReq req) {
        int result = mapper.changePic(req);
        if (result == 0) {
            return new BaseRpcResponse().failure();
        } else {
            try {
                fastDFSClientUtil.deleteFile(req.getPhoto());
                fastDFSClientUtil.deleteFile(req.getPapersphoto());
            }catch (Exception e){
                log.info("找不到文件路径，删除失败");
            }
            return new BaseRpcResponse().success();
        }
    }
}
