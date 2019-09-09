package com.biocome.platform.guard.biz;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.constant.APPConstants;
import com.biocome.platform.guard.feign.AppAccountService;
import com.biocome.platform.guard.vo.admin.AdminSummaryVo;
import com.biocome.platform.inter.basemanager.rpc.service.FileRpc;
import com.biocome.platform.inter.basemanager.biz.LesseeBiz;
import com.biocome.platform.inter.basemanager.entity.Landlord;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.inter.basemanager.mapper.LandlordMapper;
import com.biocome.platform.inter.basemanager.utils.FileUtils;
import com.biocome.platform.inter.basemanager.vo.admin.SimpleAdminVo;
import com.biocome.platform.inter.basemanager.vo.upload.ChangeLesseePicReq;
import com.biocome.platform.inter.basemanager.vo.upload.FileVo;
import com.biocome.platform.inter.gateguard.vo.user.AppAccountVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @ClassName: AdminBiz
 * @Author: zengqiang
 * @Date: 2019/8/8
 * @Description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AdminBiz extends BaseBiz<LandlordMapper, Landlord> {
    @Autowired
    FileRpc fileRpc;
    @Autowired
    LesseeBiz lesseeBiz;
    @Autowired
    AppAccountService appAccountService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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
            mapper.selectSimpleAdminList(username, type);
            return new TableResultResponse<SimpleAdminVo>(result.getTotal(), result.getResult());
        }catch (Exception e){
            log.info(e.getMessage());
            e.printStackTrace();
            return new TableResultResponse<SimpleAdminVo>(CommonConstants.EX_OTHER_CODE, "查找管理员列表失败，错误信息：数据库错误!");
        }
    }

    public BaseResponse changePic(ChangeLesseePicReq req) {
        try {
            int result = mapper.changePic(req);
            /*if (result == 0) {
                return new BaseResponse(CommonConstants.EX_OTHER_CODE, "更换头像失败，错误原因：数据库未查到该数据！");
            } else {
                List<FileVo> fileVos = FileUtils.getFileDetailByUrls("1", req.getHeadphoto(), req.getPhoto(), req.getPapersphoto());
                ObjectRestResponse objectRestResponse = fileRpc.fileDel(fileVos);
                if (objectRestResponse.getStatus() != 200) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return new BaseResponse(CommonConstants.EX_OTHER_CODE, "更换头像失败，错误原因：远程删除文件失败！");
                }
            }*/
        }catch (Exception e){
            log.info(e.getMessage());
            e.printStackTrace();
            return new BaseResponse(CommonConstants.EX_OTHER_CODE, "更换头像失败，错误原因：数据库错误！");
        }
        return new BaseResponse(CommonConstants.CODE_OK, "更新成功！");
    }

    /**
     * 增加管理员
     */
    public ObjectRestResponse<String> addAdmin(Landlord landlord) throws Exception {
        ObjectRestResponse resp = new ObjectRestResponse(CommonConstants.CODE_OK, "添加管理员成功！");
        //验证证件号唯一
        String papersnum = landlord.getPapersnum();
        if(ValidateUtils.isEmpty(papersnum)){
            resp = new ObjectRestResponse(CommonConstants.EX_CERTNO_NULL, "证件号码不能为空！");
            return resp;
        }
        boolean exists = checkExists(papersnum);
        if(exists){
            resp =  new ObjectRestResponse(CommonConstants.EX_CERTNO_EXISTS, "证件号码已存在！");
            return resp;
        }
        String usercode = UUIDUtils.generateShortUuid();
        //添加管理员
        landlord.setUsercode(usercode);
        insertSelective(landlord);
        //添加租户表
        Lessee lessee = new Lessee();
        BeanUtils.copyProperties(landlord, lessee);
        lesseeBiz.insertSelective(lessee);
        //是否开通app账号
        if(landlord.getIsapp() == APPConstants.CREATE_APP_ACCOUNT_TURE){
            AppAccountVo accountVo = new AppAccountVo();
            accountVo.setUsername(papersnum);
            accountVo.setUsercode(landlord.getUsercode());
            accountVo.setType(APPConstants.USER_TYPE_ADMIN);
            accountVo.setCreateUser(BaseContextHandler.getUsercode());
            BaseResponse baseResponse = appAccountService.createAppAccount(accountVo);
            if(baseResponse.getStatus() != CommonConstants.CODE_OK){
                return new ObjectRestResponse<String>(CommonConstants.EX_OTHER_CODE, "创建app账号失败！");
            }
        }
        resp.data(usercode);
        return resp;
    }

    private boolean checkExists(String papersnum) {
        Landlord entity = new Landlord();
        entity.setPapersnum(papersnum);
        return (mapper.selectCount(entity) == 0) ? false : true;
    }

}
