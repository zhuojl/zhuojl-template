package com.zjl.archetype.web.infra.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerDao extends BaseMapper<CustomerDO> {

    @Select("select * from customer where customer_id = #{customerId}")
    CustomerDO getByCustomerId(String customerId);
}
