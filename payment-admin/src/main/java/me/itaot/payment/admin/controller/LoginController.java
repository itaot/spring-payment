package me.itaot.payment.admin.controller;

import me.itaot.payment.beans.PaymentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:itaot
 * CreateTime:2018/7/22 22:44
 * Email:itaot.me@gmail.com
 * Description:登陆Controller
 */
@RestController
public class LoginController {

    @PostMapping(path = "/login")
    public PaymentResponse login(@RequestParam(name = "username", required = true) String username,
                                 @RequestParam(name = "password", required = true) String password) {
        PaymentResponse paymentResponse = new PaymentResponse();
        if ("admin".equals(username) && "password".equals(password)) {
            paymentResponse.setRetCode("000000");
            paymentResponse.setRetMessage("Success");
        } else {
            paymentResponse.setRetCode("-000001");
            paymentResponse.setRetMessage("用户名或密码不正确!");
        }
        return paymentResponse;
    }
}
