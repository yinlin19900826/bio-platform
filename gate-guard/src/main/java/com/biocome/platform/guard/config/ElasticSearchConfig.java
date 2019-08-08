package com.biocome.platform.guard.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
@Slf4j
public class ElasticSearchConfig {
    @Value("${elasticsearch.nodes}")
    private String nodes;
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;
    @Value("${elasticsearch.pool}")
    private String poolSize;

    @Bean(name = "transportClient")
    public TransportClient transportClient(){
        log.info("elasticsearch 初始化开始！");
        TransportClient transportClient = null;
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", clusterName)
                    .put("client.transport.sniff",true)
                    .put("thread_pool.search.size", Integer.parseInt(poolSize))
                    .build();
            transportClient = new PreBuiltTransportClient(settings);
            if(nodes == null || nodes.isEmpty()){
                log.info("错误的节点信息，elasticsearch初始化失败！");
                return transportClient;
            }
            String[] nodeArr =nodes.split(";");
            for(String node : nodeArr){
                TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(node.split(":")[0]), Integer.parseInt(node.split(":")[1]));
                transportClient.addTransportAddress(transportAddress);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("elasticsearch 初始化失败!");
        }
        return transportClient;
    }
}
