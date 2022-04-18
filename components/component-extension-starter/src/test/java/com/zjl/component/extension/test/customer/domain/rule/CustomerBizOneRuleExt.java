package com.zjl.component.extension.test.customer.domain.rule;

import com.zjl.component.exception.BizException;
import com.zjl.component.extension.Extension;
import com.zjl.component.extension.test.customer.client.Constants;
import com.zjl.component.extension.test.customer.domain.CustomerEntity;
import com.zjl.component.extension.test.customer.domain.SourceType;

/**
 * CustomerBizOneRuleExt
 *
 * @author Frank Zhang
 * @date 2018-01-07 12:10 PM
 */
@Extension(bizId = Constants.BIZ_1)
public class CustomerBizOneRuleExt implements CustomerRuleExtPt{

    @Override
    public boolean addCustomerCheck(CustomerEntity customerEntity) {
        if(SourceType.AD == customerEntity.getSourceType()){
            throw new BizException("Sorry, Customer from advertisement can not be added in this period");
        }
        return true;
    }
}
