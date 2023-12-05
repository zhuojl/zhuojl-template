package com.zjl.archetype.web.domain.customer;

import com.zjl.component.common.exception.BizException;

import lombok.Data;

//Domain Entity can choose to extend the domain model which is used for DTO
@Data
public class Customer {

    private String customerId;
    private String memberId;
    private String globalId;
    private long registeredCapital;
    private String companyName;
    private SourceType sourceType;
    private CompanyType companyType;

    public Customer() {
    }

    public void checkConflict() {
        //Per different biz, the check policy could be different, if so, use ExtensionPoint
        if ("ConflictCompanyName".equals(this.companyName)) {
        }

    }
}
