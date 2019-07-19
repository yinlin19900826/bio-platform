package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.AdminBuildingBind;
import com.biocome.platform.guard.vo.admin.AdminBuildingVo;
import com.biocome.platform.guard.vo.admin.AdminSummaryVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 管理员楼栋授权
 *
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2019-05-15 18:33:25
 */
public interface AdminBuildingBindMapper extends Mapper<AdminBuildingBind> {

    List<AdminSummaryVo> selectAdminListOnBuilding(@Param("code") String code, @Param("type") Integer type);

    List<AdminBuildingVo> selectBindlessBuilding(@Param("usercode") String usercode, @Param("buildName") String buildName, @Param("communityName") String communityName);

    void batchInsert(@Param("list") List<AdminBuildingBind> list);

    void batchDeleteByIdsAndUserCode(@Param("ids") List<Integer> ids, @Param("usercode") String usercode);

    /**
     * 根据楼栋编号查询所有房东姓名
     *
     * @param buildcode 楼栋编号
     * @return java.util.List<java.lang.String>
     * @Author shenlele
     * @Date 2019/5/20 14:31
     */
    List<String> selectNamesByCode(@Param("buildcode") String buildcode);

    List<AdminBuildingVo> adminBindBuildingList(@Param("usercode") String usercode, @Param("buildName") String buildName, @Param("communityName") String communityName, @Param("username") String username, @Param("phone") String phone);

    List<AdminBuildingBind> selectByAdmin(@Param("usercode") String usercode, @Param("buildcode") String buildcode);

}
