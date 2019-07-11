package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.AdminHouseBind;
import com.biocome.platform.admin.vo.admin.AdminHouseVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 管理员房屋授权
 * 
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2019-05-15 18:33:25
 */
public interface AdminHouseBindMapper extends Mapper<AdminHouseBind> {

    List<AdminHouseVo> bindBuildingHouseList(@Param("buildcode") String buildcode);

    void batchInsert(@Param("list") List<AdminHouseBind> list);

    void batchDeleteByIds(@Param("ids") List<Integer> ids);

    void bindAll(@Param("buildcode") String buildcode);

    void removeAll(@Param("buildcode") String buildcode);
}
