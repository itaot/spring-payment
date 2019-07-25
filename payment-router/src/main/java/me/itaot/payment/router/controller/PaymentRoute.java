package me.itaot.payment.router.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController("/route/payment/")
public class PaymentRoute {

    private static final Logger log = LoggerFactory.getLogger(PaymentRoute.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity route(){
        log.info("Start execute payment route ... ");

        Map<String,Double> formtList = new HashMap<String,Double>();
        formtList.put("UPZX",123.0);
        formtList.put("CPCN",456.0);

        return new ResponseEntity(formtList, HttpStatus.OK);
    }
}
