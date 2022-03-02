package com.alibaba.craftsman.api.demo.dto;

import com.alibaba.craftsman.api.CommonCommand;

import lombok.Data;

/**
 * MetricDeleteCmd
 *
 * @author Frank Zhang
 * @date 2019-03-01 10:11 AM
 */
@Data
public class MetricDeleteCmd extends CommonCommand {
    /**
     * Metric ID
     */
    private String metricId;
}
