package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**审核相关操作
 * @ClassName: AuditOperateMapper
 * @Author: yinlin
 * @Date: 2019/8/2 11:57
 * @Description:
 */
public interface AuditOperateMapper extends Mapper<Lessee> {

    List<Lessee> selectByAudit(@Param("isaudit") int isaudit);

}