package com.zjl.archetype.web.biz.customer

import com.zjl.archetype.web.biz.customer.service.CustomerBizServiceImpl
import com.zjl.archetype.web.client.customer.cmd.CustomerAddCmd
import com.zjl.archetype.web.client.customer.dto.CustomerDTO
import com.zjl.archetype.web.domain.customer.dto.Customer
import com.zjl.archetype.web.domain.customer.service.CustomerService
import com.zjl.archetype.web.infra.exception.BizErrorEnum
import com.zjl.component.common.exception.BadRequestException
import spock.lang.Specification

class CustomerBizServiceSpec extends Specification {
    def customerService = Mock(CustomerService)
    def customerBizService = new CustomerBizServiceImpl(customerService: customerService)

    def testAddCustomer() {
        given:
        def customerDTO = new CustomerDTO(customerId: "DEFAULT_USER_ID", customerName: "DEFAULT_USER_NAME")
        def customerAddCmd = new CustomerAddCmd(customer: customerDTO)

        and:
        customerService.getByCustomerName(_) >> null
        customerService.addCustomer(_) >> 1

        when:
        def resp = customerBizService.addCustomer(customerAddCmd);
        then:
        1 == resp.getData()
    }

    def "testAddCustomer with exception"() {
        given:
        def customerDTO = new CustomerDTO(customerId: "DEFAULT_USER_ID", customerName: "DEFAULT_USER_NAME")
        def customerAddCmd = new CustomerAddCmd(customer: customerDTO)

        and:
        customerService.getByCustomerName(_) >> new Customer()
        customerService.addCustomer(_) >> 1

        when:
        def resp = customerBizService.addCustomer(customerAddCmd);

        then:
        def exception = thrown(BadRequestException)
        exception.errCode == BizErrorEnum.CUSTOMER_NAME_REPEAT.errorCode()
    }
}
