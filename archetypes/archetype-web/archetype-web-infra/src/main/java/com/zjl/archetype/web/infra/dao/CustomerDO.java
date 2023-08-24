package com.zjl.archetype.web.infra.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("customer")
public class CustomerDO {
    private Long id;
    @TableField("customer_id")
    private String customerId;
    @TableField("member_id")
    private String memberId;
    @TableField("global_id")
    private String globalId;
    @TableField("registered_capital")
    private long registeredCapital;
    @TableField("company_name")
    private String companyName;
}
