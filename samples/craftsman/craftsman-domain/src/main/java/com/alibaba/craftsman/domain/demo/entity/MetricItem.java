package com.alibaba.craftsman.domain.demo.entity;

import com.alibaba.cola.domain.Entity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * MetricItem
 * 指标项，多个指标项可以构成一个指标
 * @author Frank Zhang
 * @date 2018-07-04 1:23 PM
 */
@Data
@Entity
@Slf4j
public abstract class MetricItem implements Measurable{

    private String id;

    /**
     * The metric this MetricItem belongs to
     */
    @JSONField(serialize = false)
    private SubMetric subMetric;


    public void setSubMetric(SubMetric subMetric){
        this.subMetric = subMetric;
    }
    /**
     * 将度量项的转成JSON
     * @return
     */
    public String toJsonString() {
        String jsonStr = JSON.toJSONString(this, JSONPropertyFilter.singleton);
        log.debug("\n From : " + this + " \n To: " + jsonStr);
        return jsonStr;
    }

}
