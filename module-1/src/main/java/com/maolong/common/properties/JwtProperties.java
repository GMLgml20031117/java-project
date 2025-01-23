package com.maolong.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "project.jwt")
public class JwtProperties {
    private String adminSecretKey;
    private Long adminTtl;
    private String adminTokenName;

}
