package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.entity.Landlord;
/*import com.bicome.platform.inter.basemanager.vo.admin.SimpleAdminVo;*/
import com.biocome.platform.inter.basemanager.vo.admin.SimpleAdminVo;
import com.biocome.platform.inter.basemanager.vo.upload.ChangeLesseePicReq;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: LandlordMapper
 * @Author: shenlele
 * @Date: 2019/5/8 14:04
 * @Description:
 */
public interface LandlordMapper extends Mapper<Landlord> {

    /**
     * 根据姓名，手机号，证件号码，房东类型查询，查询全部时传参为null
     *
     * @param username     姓名
     * @param tel          手机号
     * @param papersnum    证件号
     * @param landlordtype 房东类型
     * @return java.util.List<Landlord>
     * @Author shenlele
     * @Date 2019/5/8 14:04
     */
    List<Landlord> selectByAttribute(@Param("username") String username, @Param("tel") String tel, @Param("papersnum") String papersnum, @Param("landlordtype") Integer landlordtype);


    /**
     * 根据ID删除
     *
     * @param list 主键
     * @return int
     * @Author shenlele
     * @Date 2019/5/8 14:05
     */
    int deleteLandlord(@Param("list") List<Integer> list);

    List<SimpleAdminVo> selectSimpleAdminList(@Param("username") String username, @Param("type") Integer type);

    int changePic(@Param("req") ChangeLesseePicReq req);

    List<Landlord> selectByUserCode(@Param("usercode") String usercode);
}
