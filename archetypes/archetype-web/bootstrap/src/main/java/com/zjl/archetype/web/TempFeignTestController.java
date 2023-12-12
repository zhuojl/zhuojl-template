package com.zjl.archetype.web;

import com.zjl.archetype.web.infra.dao.CustomerDO;
import com.zjl.archetype.web.infra.feign.FeignTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TempFeignTestController {
    @Autowired
    private FeignTest feignTest;

    @PostMapping(value = "/internal/testFeign11", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CustomerDO testFeignInternal() {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerId("asdfadsf");
        return customerDO;
    }

    @PostMapping(value = "/testFeign", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CustomerDO testFeign() {
        log.info("测试中文");
        Map<String, String> param = new HashMap<>();
        param.put("aa", "bb");
        param.put("aaaa", "bbbb");
        feignTest.test(param);
        //feignTest.test(JSONObject.toJSONString(param));
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerId("asdfadsf");
        return customerDO;
    }

    @GetMapping(value = "/testFeign44444")
    public CustomerDO testFeign44444() {

        //feignTest.test(JSONObject.toJSONString(param));
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerId("asdfadsf");
        return customerDO;
    }

    @PostMapping(value = "/internal/testFeign2", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CustomerDO testFeignInternal2(CustomerDO customerDO) throws InterruptedException {
        return customerDO;
    }

    @PostMapping(value = "/testFeign2", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CustomerDO testFeign2(@ModelAttribute CustomerDO customerDO) {
        // @RequestBody 不能与 application/x-www-form-urlencoded 混用，虽然http请求中参数在body。
        // public CustomerDO testFeign2(@RequestBody CustomerDO customerDO) {
        String bodyStr = "customerId=11111&memberId=耍到耍到";
        return feignTest.test2(bodyStr);
    }

    @PostMapping(value = "/internal/testFeign22", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String testFeignInternal22(CustomerDO customerDO) throws InterruptedException {
        return "String";
    }
    @PostMapping(value = "/testFeign22", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String testFeign22(@ModelAttribute CustomerDO customerDO) {
        // @RequestBody 不能与 application/x-www-form-urlencoded 混用，虽然http请求中参数在body。
        // public CustomerDO testFeign2(@RequestBody CustomerDO customerDO) {
        String bodyStr = "customerId=11111&memberId=耍到耍到";
        return feignTest.test22(bodyStr);
    }

    @PostMapping(value = "/testFeign3", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CustomerDO testFeign3(CustomerDO customerDO) {
        // @RequestBody 不能与 application/x-www-form-urlencoded 混用，虽然http请求中参数在body。
        // public CustomerDO testFeign2(@RequestBody CustomerDO customerDO) {
        String bodyStr = "customerId=11111&memberId=memberId";
        feignTest.test3(customerDO);
        customerDO.setCustomerId("asdfadsf");
        return customerDO;
    }

    @PostMapping(value = "/testFeign4", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CustomerDO testFeign4(CustomerDO customerDO) {

        return new CustomerDO();
    }

    @PostMapping(value = "/testFeign5", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDO testFeign5(@RequestBody CustomerDO customerDO) {
        return customerDO;
    }
}
