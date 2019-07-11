package com.biocome.platform.guard.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员楼栋授权
 *
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2019-05-15 18:33:25
 */
public interface AdminBuildingBindMapper {

    /**
     * 根据楼栋编号查询所有房东姓名
     *
     * @param buildcode 楼栋编号
     * @return java.util.List<java.lang.String>
     * @Author shenlele
     * @Date 2019/5/20 14:31
     */
    List<String> selectNamesByCode(@Param("buildcode") String buildcode);
}
