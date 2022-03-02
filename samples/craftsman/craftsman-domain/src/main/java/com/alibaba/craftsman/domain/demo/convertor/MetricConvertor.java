package com.alibaba.craftsman.domain.demo.convertor;


import com.alibaba.craftsman.dao.demo.dataobject.MetricDO;
import com.alibaba.craftsman.domain.demo.entity.MetricItem;

/**
 * @author frankzhang
 */
public class MetricConvertor{

    public static MetricDO toDataObject(MetricItem metricItem){
        MetricDO metricDO = new MetricDO();
        metricDO.setUserId("1");
        metricDO.setMainMetric(metricItem.getSubMetric().getParent().getCode());
        metricDO.setSubMetric(metricItem.getSubMetric().getCode());
        metricDO.setMetricItem(metricItem.toJsonString());
        metricDO.setCreator("test");
        metricDO.setModifier("test");
        return metricDO;
    }

}
