package com.zjl.component.extension.test.customer.client;


import com.zjl.component.dto.Command;
import com.zjl.component.extension.BizScenario;
import lombok.Data;

/**
 * AddCustomerCmd
 *
 * @author Frank Zhang 2018-01-06 7:28 PM
 */
@Data
public class AddCustomerCmd extends Command {

    private CustomerDTO customerDTO;

    private String biz;

    private BizScenario bizScenario;
}
