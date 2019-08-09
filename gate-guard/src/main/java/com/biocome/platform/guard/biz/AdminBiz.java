package com.biocome.platform.guard.biz;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.constant.AppConstant;
import com.biocome.platform.inter.basemanager.rpc.service.FileRpc;
import com.biocome.platform.inter.basemanager.biz.LesseeBiz;
import com.biocome.platform.inter.basemanager.entity.Landlord;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.inter.basemanager.mapper.LandlordMapper;
import com.biocome.platform.inter.basemanager.utils.FileUtils;
import com.biocome.platform.inter.basemanager.vo.admin.SimpleAdminVo;
import com.biocome.platform.inter.basemanager.vo.upload.ChangeLesseePicReq;
import com.biocome.platform.inter.basemanager.vo.upload.FileVo;
import com.biocome.platform.inter.gateguard.biz.AppUserBiz;
import com.biocome.platform.inter.gateguard.entity.AppUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    AppUserBiz appUserBiz;
    @Autowired
    LesseeBiz lesseeBiz;

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

    public BaseRpcResponse changePic(ChangeLesseePicReq req)  throws Exception{
        int result = mapper.changePic(req);
        if (result == 0) {
            return new BaseRpcResponse().failure();
        } else {
            List<FileVo> fileVos = FileUtils.getFileDetailByUrls("1", req.getHeadphoto(), req.getPhoto(), req.getPapersphoto());
            ObjectRestResponse objectRestResponse = fileRpc.fileDel(fileVos);
            if (objectRestResponse.getStatus() != 200) {
                throw new Exception("远程删除文件失败");
            }
            return new BaseRpcResponse().success();
        }
    }

    /**
     * 增加管理员
     */
    public ObjectRestResponse addAdmin(Landlord landlord) throws Exception {
        //验证证件号唯一
        String papersnum = landlord.getPapersnum();
        if(ValidateUtils.isEmpty(papersnum)){
            return new ObjectRestResponse(CommonConstants.EX_CERTNO_NULL, "证件号码不能为空！");
        }
        boolean exists = checkExists(papersnum);
        if(exists){
            return new ObjectRestResponse(CommonConstants.EX_CERTNO_EXISTS, "证件号码已存在！");
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
        if(landlord.getIsapp() == AppConstant.OPEN_APP_ACCOUNT_TURE){
            AppUser appUser = new AppUser();
            String certNo = landlord.getPapersnum();
            appUser.setUsername(certNo);
            appUser.setPassword(encoder.encode(certNo.substring((certNo.length()-1) - 6)));
            appUser.setUsercode(usercode);
            appUser.setType(AppConstant.APP_USER_TYPE_ADMIN);
            appUser.setIscomplete(AppConstant.APP_INFORMATION_NOT_COMPLETE);
            appUser.setCreateUser(BaseContextHandler.getUsername());
            appUser.setCreateTime(new Date());
            appUserBiz.insert(appUser);
        }
        return new ObjectRestResponse().success();
    }

    private boolean checkExists(String papersnum) {
        Landlord entity = new Landlord();
        entity.setPapersnum(papersnum);
        return (mapper.selectCount(entity) == 0) ? false : true;
    }
}
