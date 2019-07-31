package com.biocome.platform.wechatapplet.utils;

import com.biocome.platform.inter.basemanager.constant.BrandEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UriUtil
 * @Author: shenlele
 * @Date: 2019/7/11 10:42
 * @Description:
 */
@Component
@Slf4j
public class UriUtil {
    @Value("#{${differentBrandUri}}")
    Map<String, String> differentBrandUri = new HashMap<>();

    public URI getUriByBrand(String brand) {
        String brandName = BrandEnum.getBrandByBrandCode(brand);
        if (StringUtils.isNotBlank(brandName)) {
            try {
                return new URI("http://" + differentBrandUri.get(brandName));
            } catch (URISyntaxException e) {
                log.error("没有对应品牌的设备或配置不完全");
            }
        }
        return null;
    }
}
