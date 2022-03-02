package com.alibaba.craftsman.biz.demo.service;

import java.util.List;

import com.alibaba.craftsman.api.demo.dto.ATAMetricAddCmd;
import com.alibaba.craftsman.api.demo.dto.ATAMetricQry;
import com.alibaba.craftsman.api.demo.dto.MetricDeleteCmd;
import com.alibaba.craftsman.api.demo.dto.clientobject.ATAMetricCO;

import org.springframework.stereotype.Service;

/**
 * MetricsServiceImpl
 *
 * @author Frank Zhang
 * @date 2019-03-01 11:41 AM
 */
@Service
public interface MetricsBizService {

    String addATAMetric(ATAMetricAddCmd cmd);

    Boolean deleteMetric(MetricDeleteCmd cmd);

    List<ATAMetricCO> listATAMetrics(ATAMetricQry ataMetricQry);
}
