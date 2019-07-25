package me.itaot.payment.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.itaot.payment.beans.PaymentResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"支付接口"})
public class PaymentController {

    @ApiOperation(value = "支付转账")
    @RequestMapping(path = "/payment", method = RequestMethod.POST)
    public PaymentResponse payment(String message) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setRetCode("000000");
        return paymentResponse;
    }

    @ApiOperation(value = "状态查询")
    @RequestMapping(path = "/payment", method = RequestMethod.GET)
    public PaymentResponse query(String message) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setRetCode("000000");
        return paymentResponse;
    }

    @ApiOperation(value = "撤销转账")
    @RequestMapping(path = "/payment", method = RequestMethod.DELETE)
    public PaymentResponse cancel(String message) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setRetCode("000000");
        return paymentResponse;
    }

}