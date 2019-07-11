package com.biocome.platform.admin.utils;

import com.biocome.platform.admin.constant.BrandEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hxy
 * @date 2019/5/14 11:38
 */
@Component
public class UriUtil {
    @Value("#{${differentBrandUri}}")
    Map<String,String> differentBrandUri = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(UriUtil.class);

    public URI getUriByBrand(String brand) {
        String brandName = BrandEnum.getBrandByBrandCode(brand);
        if (StringUtils.isNotBlank(brandName)) {
            String uristr = differentBrandUri.get(brandName);
            try {
                URI uri = new URI("http://"+uristr);
                return uri;
            } catch (URISyntaxException e) {
                logger.error("没有对应品牌的设备或配置不完全");
            }
        }
        return null;
    }
}
