package com.alibaba.craftsman.api.demo;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.craftsman.api.demo.dto.ATAMetricAddCmd;
import com.alibaba.craftsman.api.demo.dto.ATAMetricQry;
import com.alibaba.craftsman.api.demo.dto.MetricDeleteCmd;
import com.alibaba.craftsman.api.demo.dto.clientobject.ATAMetricCO;

/**
 * MetricsServiceI
 *
 * @author Frank Zhang
 * @date 2019-03-01 10:06 AM
 */
public interface MetricsServiceI {
    Response addATAMetric(ATAMetricAddCmd cmd);
    Response deleteMetric(MetricDeleteCmd cmd);
    MultiResponse<ATAMetricCO> listATAMetrics(ATAMetricQry ataMetricQry);
}
