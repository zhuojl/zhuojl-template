package com.zjl.component.extension.test.customer.app.extension;

import com.zjl.component.exception.BizException;
import com.zjl.component.extension.Extension;
import com.zjl.component.extension.test.customer.client.AddCustomerCmd;
import com.zjl.component.extension.test.customer.client.Constants;
import com.zjl.component.extension.test.customer.app.extensionpoint.AddCustomerValidatorExtPt;

/**
 * AddCustomerBizTwoValidator
 *
 * @author Frank Zhang
 * @date 2018-01-07 1:31 AM
 */
@Extension(bizId = Constants.BIZ_2)
public class AddCustomerBizTwoValidator implements AddCustomerValidatorExtPt {

    public void validate(AddCustomerCmd addCustomerCmd) {
        //For BIZ TWO CustomerTYpe could not be null
        if (addCustomerCmd.getCustomerDTO().getCustomerType() == null)
            throw new BizException("CustomerType could not be null");
    }
}
