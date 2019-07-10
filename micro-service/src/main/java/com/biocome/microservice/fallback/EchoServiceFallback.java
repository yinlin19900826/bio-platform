package com.biocome.microservice.fallback;

import com.biocome.microservice.feign.EchoService;

public class EchoServiceFallback implements EchoService {
    @Override
    public String echo(String word) {
        return "调用 echo() 失败 "+word;
    }
}
