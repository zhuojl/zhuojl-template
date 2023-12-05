package com.zjl.archetype.web;

import java.util.HashMap;
import java.util.Map;

import com.zjl.archetype.web.infra.dao.CustomerDO;
import com.zjl.archetype.web.infra.feign.FeignTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempFeignTestController {
    @Autowired
    private FeignTest feignTest;

    @PostMapping(value = "/internal/testFeign", consumes = "application/x-www-form-urlencoded")
    public CustomerDO testFeignInternal() {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerId("asdfadsf");
        return customerDO;
    }
    @PostMapping(value = "/testFeign", consumes = "application/x-www-form-urlencoded")
    public CustomerDO testFeign() {
        Map<String,String> param = new HashMap<>();
        param.put("aa", "bb");
        param.put("aaaa", "bbbb");
        feignTest.test(param);
        //feignTest.test(JSONObject.toJSONString(param));
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerId("asdfadsf");
        return customerDO;
    }

    @PostMapping(value = "/internal/testFeign2", consumes = "application/x-www-form-urlencoded")
    public CustomerDO testFeignInternal2(CustomerDO customerDO) {
        return customerDO;
    }
    @PostMapping(value = "/testFeign2", consumes = "application/x-www-form-urlencoded")
    public CustomerDO testFeign2(@ModelAttribute CustomerDO customerDO) {
    // @RequestBody 不能与 application/x-www-form-urlencoded 混用，虽然http请求中参数在body。
    // public CustomerDO testFeign2(@RequestBody CustomerDO customerDO) {
        String bodyStr = "customerId=11111&memberId=耍到耍到";
        feignTest.test2(bodyStr);
        customerDO.setCustomerId("asdfadsf");
        return customerDO;
    }
    @PostMapping(value = "/testFeign3", consumes = "application/x-www-form-urlencoded")
    public CustomerDO testFeign3(CustomerDO customerDO) {
    // @RequestBody 不能与 application/x-www-form-urlencoded 混用，虽然http请求中参数在body。
    // public CustomerDO testFeign2(@RequestBody CustomerDO customerDO) {
        String bodyStr = "customerId=11111&memberId=memberId";
        feignTest.test3(customerDO);
        customerDO.setCustomerId("asdfadsf");
        return customerDO;
    }
    @PostMapping(value = "/testFeign4", consumes = "application/x-www-form-urlencoded")
    public CustomerDO testFeign4(CustomerDO customerDO) {

        return new CustomerDO();
    }
    @PostMapping(value = "/testFeign5", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String testFeign5(@RequestBody CustomerDO customerDO) {
        return "1";
    }
}
