package com.cyq.cyq.config;

import lombok.Data;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Data
@Component
@ConfigurationProperties(prefix = "spring.es")
public class ElasticsearchConfig {
    private String host;
    private Integer port;

    @Bean
    public TransportClient client() throws UnknownHostException {
        // 设置es节点的配置信息
        Settings settings = Settings.builder().put("cluster.name", "myClusterName").build();
        // 实例化es的客户端对象
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));

        return client;
    }
}
