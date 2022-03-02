package com.alibaba.craftsman.biz.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.craftsman.api.demo.dto.ATAMetricAddCmd;
import com.alibaba.craftsman.api.demo.dto.ATAMetricQry;
import com.alibaba.craftsman.api.demo.dto.MetricDeleteCmd;
import com.alibaba.craftsman.api.demo.dto.clientobject.ATAMetricCO;
import com.alibaba.craftsman.biz.demo.service.MetricsBizService;
import com.alibaba.craftsman.dao.demo.MetricMapper;
import com.alibaba.craftsman.dao.demo.dataobject.MetricDO;
import com.alibaba.craftsman.domain.demo.entity.SubMetricType;
import com.alibaba.craftsman.domain.demo.entity.techinfluence.ATAMetricItem;
import com.alibaba.craftsman.domain.demo.service.MetricService;
import com.alibaba.fastjson.JSON;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MetricsServiceImpl
 *
 * @author Frank Zhang
 * @date 2019-03-01 11:41 AM
 */
@Service
@CatchAndLog
public class MetricsBizServiceImpl implements MetricsBizService {

    @Autowired
    private MetricService metricService;
    @Resource
    private MetricMapper metricMapper;

    @Override
    public String addATAMetric(ATAMetricAddCmd cmd) {

        ATAMetricItem ataMetricItem = new ATAMetricItem();
        BeanUtils.copyProperties(cmd.getAtaMetricCO(), ataMetricItem);
        return metricService.save(ataMetricItem);
    }

    @Override
    public Boolean deleteMetric(MetricDeleteCmd cmd) {
        return metricMapper.delete(cmd.getMetricId(), cmd.getOperater()) == 1;
    }

    @Override
    public List<ATAMetricCO> listATAMetrics(ATAMetricQry ataMetricQry) {
        List<MetricDO> metricDOList = metricMapper.listBySubMetric(ataMetricQry.getOwnerId(),
            SubMetricType.ATA.getMetricSubTypeCode());
        List<ATAMetricCO> ataMetricCOList = new ArrayList<>();
        metricDOList.forEach(metricDO -> {
            ATAMetricCO ataMetricCO = JSON.parseObject(metricDO.getMetricItem(), ATAMetricCO.class);
            ataMetricCO.setOwnerId(metricDO.getUserId());
            ataMetricCOList.add(ataMetricCO);
        });
        return ataMetricCOList;
    }
}
