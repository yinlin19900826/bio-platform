package com.biocome.platform.inter.basemanager.biz;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.inter.basemanager.mapper.LesseeMapper;
import com.biocome.platform.inter.basemanager.vo.LesseeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: LesseeBiz
 * @Author: shenlele
 * @Date: 2019/5/7 10:21
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LesseeBiz extends BaseBiz<LesseeMapper, Lessee> {

    private Logger log = LoggerFactory.getLogger(LesseeBiz.class);

    @Autowired
    private LesseeMapper lesseeMapper;

    /**
     * 查询租户信息(可传值有estatecode，islogout，username，papersnum，usersex，occupation，politicsstatus，
     * birthbegintime，birthendtime，registerbegintime，registerendtime)
     *
     * @param pageSize 分页数量
     * @param pageNum  分页页码
     * @param lesseeVo 可传值有estatecode，islogout，username，papersnum，usersex，occupation，politicsstatus，birthbegintime，birthendtime，registerbegintime，registerendtime
     * @return com.github.wxiaoqi.security.common.msg.TableResultResponse<Lessee>
     * @Author shenlele
     * @Date 2019/5/8 14:12
     */
    public TableResultResponse<Lessee> selectByAttribute(int pageSize, int pageNum, LesseeVo lesseeVo) {
        TableResultResponse<Lessee> res;
        try {
            if (ValidateUtils.isNotEmpty(lesseeVo.getAgetype())) {
                //处理时间
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int type = lesseeVo.getAgetype();
                if (type == 0) {
                    //0-16岁
                    lesseeVo.setBirthbegintime(date);
                    cal.add(Calendar.YEAR, -16);
                    lesseeVo.setBirthendtime(cal.getTime());
                } else if (type == 1) {
                    //16-65岁
                    cal.add(Calendar.YEAR, -16);
                    lesseeVo.setBirthbegintime(cal.getTime());
                    cal.setTime(date);
                    cal.add(Calendar.YEAR, -65);
                    lesseeVo.setBirthendtime(cal.getTime());
                } else {
                    //65岁以上
                    cal.add(Calendar.YEAR, -65);
                    lesseeVo.setBirthbegintime(cal.getTime());
                }
            }
            Page<Lessee> result = PageHelper.startPage(pageNum, pageSize);
            lesseeMapper.selectByAttribute(lesseeVo);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询租户信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询租户信息失败!");
        }
        return res;
    }

    /**
     * 根据ID删除
     *
     * @param idList 主键
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse<Lessee>
     * @Author shenlele
     * @Date 2019/5/8 14:13
     */
    public ObjectRestResponse<Lessee> deleteLessee(String idList) throws Exception {
        lesseeMapper.deleteLessee(IdUtils.getIds(idList));
        return new ObjectRestResponse<>(true);
    }

    /**
     * 保存租户信息（人员编号唯一）
     *
     * @param lessee 实体类
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/22 19:51
     */
    public ObjectRestResponse insertLessee(Lessee lessee) throws Exception {
        lessee.setUsercode(lessee.getPapersnum());
        Lessee lessee1 = new Lessee();
        lessee1.setUsercode(lessee.getUsercode());
        List<Lessee> lessees = lesseeMapper.select(lessee1);
        if (ValidateUtils.isEmpty(lessees)) {
            lesseeMapper.insertSelective(lessee);
            return new ObjectRestResponse().success();
        } else {
            throw new Exception("保存失败！人员编号已存在！");
        }
    }

}
