package com.zjl.component.extension.test.customer.app.extension;

import com.zjl.component.extension.Extension;
import com.zjl.component.extension.test.customer.client.AddCustomerCmd;
import com.zjl.component.extension.test.customer.client.Constants;
import com.zjl.component.extension.test.customer.app.extensionpoint.AddCustomerValidatorExtPt;

/**
 * AddCustomerBiz1UseCase1Validator
 *
 * @author Frank Zhang
 * @date 2020-08-20 12:58 PM
 */
@Extension(bizId = Constants.BIZ_1, useCase = Constants.USE_CASE_1)
public class AddCustomerBiz1UseCase1Validator implements AddCustomerValidatorExtPt {
    public void validate(AddCustomerCmd addCustomerCmd) {
        System.out.println("Do validation for Biz_One's Use_Case_One");
    }
}
