#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test;

import com.alibaba.cola.dto.Response;
import ${package}.api.customer.CustomerServiceI;
import ${package}.api.customer.dto.CustomerAddCmd;
import ${package}.api.customer.dto.data.CustomerDTO;
import ${package}.api.customer.dto.data.ErrorCode;
import ${package}.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This is for integration test.
 *
 * Created by fulan.zjf on 2017/11/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerServiceI customerService;


    @Before
    public void setUp() {

    }

    @Test
    public void testCustomerAddSuccess(){
        //1.prepare
        CustomerAddCmd customerAddCmd = new CustomerAddCmd();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCompanyName("NormalName");
        customerAddCmd.setCustomerDTO(customerDTO);

        //2.execute
        Response response = customerService.addCustomer(customerAddCmd);

        //3.assert
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testCustomerAddCompanyNameConflict(){
        //1.prepare
        CustomerAddCmd customerAddCmd = new CustomerAddCmd();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCompanyName("ConflictCompanyName");
        customerAddCmd.setCustomerDTO(customerDTO);

        //2.execute
        Response response = customerService.addCustomer(customerAddCmd);

        //3.Exception
        Assert.assertEquals(ErrorCode.B_CUSTOMER_companyNameConflict, response.getErrCode());

    }
}
