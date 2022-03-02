package com.alibaba.craftsman.web.demo;

import java.util.List;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.craftsman.api.demo.MetricsServiceI;
import com.alibaba.craftsman.biz.demo.service.MetricsBizService;
import com.alibaba.craftsman.api.demo.dto.ATAMetricAddCmd;
import com.alibaba.craftsman.api.demo.dto.ATAMetricQry;
import com.alibaba.craftsman.api.demo.dto.MetricDeleteCmd;
import com.alibaba.craftsman.api.demo.dto.clientobject.ATAMetricCO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController implements MetricsServiceI {

    @Autowired
    private MetricsBizService metricsBizService;

    @Override
    @PostMapping(value = "/metrics/ata")
    public Response addATAMetric(@RequestBody ATAMetricAddCmd cmd) {
        return Response.of(metricsBizService.addATAMetric(cmd));
    }


    @Override
    @PostMapping(value = "/metrics/delete")
    public Response deleteMetric(@RequestBody MetricDeleteCmd cmd) {
        metricsBizService.deleteMetric(cmd);
        return Response.buildSuccess();
    }

    @Override
    @GetMapping(value = "/metrics/ata")
    public MultiResponse<ATAMetricCO> listATAMetrics(ATAMetricQry ataMetricQry) {
        List<ATAMetricCO> result = metricsBizService.listATAMetrics(ataMetricQry);
        return MultiResponse.of(result);
    }
}
