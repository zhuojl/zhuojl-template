package com.zjl.archetype.web.infra.dao.customer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjl.archetype.web.constants.customer.CustomerType;
import lombok.Data;

@Data
@TableName("customer")
public class CustomerDO {

    private Long id;
    @TableField("customer_id")
    private String customerId;
    @TableField("customer_name")
    private String customerName;
    @TableField("member_id")
    private String memberId;
    @TableField("global_id")
    private String globalId;
    @TableField("registered_capital")
    private long registeredCapital;

    @TableField("customer_type")
    private CustomerType customerType;
}
