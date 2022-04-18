package com.zjl.component.extension.test.customer.app;

import com.zjl.component.dto.Response;

import com.zjl.component.extension.test.customer.client.AddCustomerCmd;
import com.zjl.component.extension.test.customer.client.CustomerDTO;
import com.zjl.component.extension.test.customer.client.CustomerServiceI;
import com.zjl.component.extension.test.customer.client.GetOneCustomerQry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * CustomerServiceImpl
 *
 * @author Frank Zhang 2018-01-06 7:40 PM
 */
@Service
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private AddCustomerCmdExe addCustomerCmdExe;

    @Resource
    private GetOneCustomerQryExe getOneCustomerQryExe;


    @Override
    public Response addCustomer(AddCustomerCmd addCustomerCmd) {
        return addCustomerCmdExe.execute(addCustomerCmd);
    }

    @Override
    public Response<CustomerDTO> getCustomer(GetOneCustomerQry getOneCustomerQry) {
        return getOneCustomerQryExe.execute(getOneCustomerQry);
    }
}
