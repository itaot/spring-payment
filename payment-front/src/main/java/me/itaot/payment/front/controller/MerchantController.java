package me.itaot.payment.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.itaot.payment.front.beans.MerchantInfo;
import me.itaot.payment.front.services.MerchantInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author:itaot
 * CreateTime:2018/7/7 20:55
 * Email:itaot.me@gmail.com
 * Description:商户信息管理
 */
@Api(value = " ",tags = "商户信息管理")
@RestController
public class MerchantController {

    @Autowired
    private MerchantInfoService merchantInfoService;

    private static final Logger log = LoggerFactory.getLogger(MerchantController.class);


    @ApiOperation(value = "商户信息获取")
    @RequestMapping(path = "merchant", method = RequestMethod.GET)
    public List<MerchantInfo> list(MerchantInfo merchantInfo) {
        log.info("Start execute query merchant info ... ");
        List<MerchantInfo> merchantInfoList = merchantInfoService.list(merchantInfo);
        log.info("End execute query merchant info ...");
        return merchantInfoList;
    }

}
