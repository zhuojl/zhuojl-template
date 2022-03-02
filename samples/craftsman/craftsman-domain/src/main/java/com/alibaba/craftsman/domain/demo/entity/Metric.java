package com.alibaba.craftsman.domain.demo.entity;

import com.alibaba.cola.domain.Entity;

/**
 * Metric
 * 指标
 * @author Frank Zhang
 * @date 2018-07-04 1:23 PM
 */
@Entity
public abstract class Metric implements Measurable{

    private double score;


    public Metric(){

    }

    /**
     * 度量名称，用于UI显示
     * @return
     */
    abstract public String getName();

    /**
     * 度量Code，用于数据库存储
     * @return
     */
    abstract public String getCode();

    abstract public double getWeight();

    @Override
    public String toString(){
        return this.getName() + " " + this.score;
    }

}
