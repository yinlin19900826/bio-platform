package com.biocome.platform.guard.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hxy
 * @date 2019/5/7 17:04
 */
public enum BrandEnum {
    /**
     * 各个品牌
     */
    BIOCOME("奔凯", "1"),
    XIONGDI("雄帝", "2"),
    LIZE("丽泽", "3"),
    HAINENG("海能", "4");
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 品牌编码
     */
    private String brandCode;

    private BrandEnum(String brandName, String brandCode) {
        this.brandName = brandName;
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    /**
     * 获取所有品牌
     *
     * @return
     */
    public static List<Map<String, String>> getAllBrand() {
        List<Map<String, String>> list = new ArrayList<>();
        for (BrandEnum brandEnum : BrandEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("brandCode", brandEnum.getBrandCode());
            map.put("brandName", brandEnum.getBrandName());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据品牌码获取品牌名称
     *
     * @return
     */
    public static String getBrandByBrandCode(String brandCode) {
        for (BrandEnum brandEnum : BrandEnum.values()) {
            if (brandEnum.getBrandCode().equals(brandCode)) {
                return brandEnum.name();
            }
        }
        return null;
    }
    public static Integer getBrandNum(){
        return BrandEnum.values().length;
    }
}
