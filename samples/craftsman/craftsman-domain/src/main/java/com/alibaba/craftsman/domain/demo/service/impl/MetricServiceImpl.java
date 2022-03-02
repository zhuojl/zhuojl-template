package com.alibaba.craftsman.domain.demo.service.impl;

import javax.annotation.Resource;

import com.alibaba.craftsman.api.demo.dto.domainevent.MetricItemCreatedEvent;
import com.alibaba.craftsman.client.AppMetricMapper;
import com.alibaba.craftsman.client.BugMetricMapper;
import com.alibaba.craftsman.common.event.DomainEventPublisher;
import com.alibaba.craftsman.dao.demo.MetricMapper;
import com.alibaba.craftsman.dao.demo.dataobject.MetricDO;
import com.alibaba.craftsman.domain.demo.convertor.MetricConvertor;
import com.alibaba.craftsman.domain.demo.entity.MetricItem;
import com.alibaba.craftsman.domain.demo.service.MetricService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MetricGatewayImpl
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:20 PM
 */
@Component
@Slf4j
public class MetricServiceImpl implements MetricService {

    @Resource
    private MetricMapper metricMapper;

    @Resource
    private BugMetricMapper bugMetricMapper;

    @Resource
    private AppMetricMapper appMetricClient;

    @Resource
    private DomainEventPublisher domainEventPublisher;

    public String save(MetricItem metricItem) {
        MetricDO metricDO = MetricConvertor.toDataObject(metricItem);

        metricMapper.create(metricDO);

        log.debug("AutoGeneratedId: " + metricDO.getId());
        MetricItemCreatedEvent metricItemCreatedEvent = new MetricItemCreatedEvent();
        metricItemCreatedEvent.setId(metricDO.getId());
        metricItemCreatedEvent.setUserId(metricDO.getUserId());
        metricItemCreatedEvent.setMainMetricType(metricDO.getMainMetric());
        domainEventPublisher.publish(metricItemCreatedEvent);
        return metricDO.getId();
    }

}
