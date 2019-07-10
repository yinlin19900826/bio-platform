package com.biocome.microservice.feign;

import com.biocome.microservice.fallback.EchoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "echo", fallback = EchoServiceFallback.class)
public interface EchoService {
    @RequestMapping("api/echo/{word}")
    String echo(@PathVariable("word") String word);
}
