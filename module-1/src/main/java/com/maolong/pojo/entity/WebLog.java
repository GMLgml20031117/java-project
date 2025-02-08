package com.maolong.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebLog {
    private Long id;
    String url;
    String httpMethod;
    String classMethod;
    String ip;
    String args;
    Long timeCost;
    String userName;
    LocalDateTime visitTime;
}
