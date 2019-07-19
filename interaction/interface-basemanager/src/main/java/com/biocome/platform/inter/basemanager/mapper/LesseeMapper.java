package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.inter.basemanager.vo.LesseeVo;
import com.biocome.platform.inter.basemanager.vo.syncho.BuildAndBrandVo;
import com.biocome.platform.inter.basemanager.vo.syncho.LesseeUserVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: LesseeMapper
 * @Author: shenlele
 * @Date: 2019/5/8 14:06
 * @Description:
 */
public interface LesseeMapper extends Mapper<Lessee> {

    /**
     * 查询租户信息(可传值有estatecode，islogout，username，papersnum，usersex，occupation，politicsstatus，birthbegintime，birthendtime，registerbegintime，registerendtime)
     *
     * @param lesseeVo 可传值有estatecode，islogout，username，papersnum，usersex，occupation，politicsstatus，birthbegintime，birthendtime，registerbegintime，registerendtime
     * @return java.util.List<Lessee>
     * @Author shenlele
     * @Date 2019/5/8 14:06
     */
    List<Lessee> selectByAttribute(LesseeVo lesseeVo);

    /**
     * 根据ID删除
     *
     * @param list 主键编号
     * @return
     * @Author shenlele
     * @Date 2019/5/8 13:56
     */
    void deleteLessee(@Param("list") List<Integer> list);

    /**
     * 根据楼栋所属区域代码与厂家编号返回特定类
     *
     * @param vo 参数
     * @return java.util.List<com.github.wxiaoqi.security.admin.vo.LesseeUserVo>
     * @Author shenlele
     * @Date 2019/5/15 14:25
     */
    List<LesseeUserVo> selectByVo(@Param("vo") BuildAndBrandVo vo);

}
