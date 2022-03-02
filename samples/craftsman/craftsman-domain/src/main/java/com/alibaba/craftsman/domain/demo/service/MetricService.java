package com.alibaba.craftsman.domain.demo.service;

import com.alibaba.craftsman.domain.demo.entity.MetricItem;

/**
 * MetricGateway
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:16 PM
 */
public interface MetricService {
    String save(MetricItem metricItem);
}
