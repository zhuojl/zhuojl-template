package com.zjl.component.extension.test.customer.client;

import com.zjl.component.dto.DTO;
import com.zjl.component.extension.test.customer.domain.CustomerType;


/**
 * CustomerDTO
 *
 * @author Frank Zhang 2018-01-06 7:30 PM
 */
public class CustomerDTO extends DTO {

    private String companyName;
    private String source;  //advertisement, p4p, RFQ, ATM
    private CustomerType customerType; //potential, intentional, important, vip

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
