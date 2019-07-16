package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.inter.basemanager.vo.LesseeVo;
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

}
