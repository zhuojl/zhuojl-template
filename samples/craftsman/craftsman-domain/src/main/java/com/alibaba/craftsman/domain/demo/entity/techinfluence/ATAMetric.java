package com.alibaba.craftsman.domain.demo.entity.techinfluence;

import com.alibaba.craftsman.domain.demo.entity.MainMetric;
import com.alibaba.craftsman.domain.demo.entity.SubMetric;
import com.alibaba.craftsman.domain.demo.entity.SubMetricType;

/**
 * ATAMetric
 * ATA文章指标
 * @author Frank Zhang
 * @date 2018-07-04 1:24 PM
 */
public class ATAMetric extends SubMetric {

    public ATAMetric(){
        this.subMetricType = SubMetricType.ATA;
    }

    public ATAMetric(MainMetric parent) {
        this.parent = parent;
        parent.addSubMetric(this);
        this.subMetricType = SubMetricType.ATA;
    }

    @Override
    public double getWeight() {
        return  1;
    }
}
