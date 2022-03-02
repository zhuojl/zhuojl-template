package com.alibaba.craftsman.api.demo.dto.domainevent;

import lombok.Data;

@Data
public class MetricItemCreatedEvent {

    private String id;

    private String userId;

    private String mainMetricType;
}
