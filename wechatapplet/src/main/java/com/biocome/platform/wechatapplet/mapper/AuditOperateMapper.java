package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.wechatapplet.vo.common.AuditOperateVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**审核相关操作
 * @ClassName: AuditOperateMapper
 * @Author: yinlin
 * @Date: 2019/8/2 11:57
 * @Description:
 */
public interface AuditOperateMapper extends Mapper<AuditOperateVo> {

    List<Lessee> selectByAudit(@Param("isaudit") int isaudit);


    /**
     * 根据用户名更改审核状态
     *
     * @param
     * @return void
     * @Author yinlin
     * @Date 2019/8/5 10:38
     */
    void updateIsAudit(@Param("username") String username,@Param("isaudit") Integer isaudit);

}