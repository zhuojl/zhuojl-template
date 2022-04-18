package com.zjl.component.extension.test.customer.client;

import com.zjl.component.dto.Response;

/**
 * CustomerServiceI
 *
 * @author Frank Zhang 2018-01-06 7:24 PM
 */
public interface CustomerServiceI {
    public Response addCustomer(AddCustomerCmd addCustomerCmd);
    public Response<CustomerDTO> getCustomer(GetOneCustomerQry getOneCustomerQry);
}
