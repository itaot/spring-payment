package me.itaot.payment.cpcn.controller;

import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.cpcn.service.CpcnMesssageService;
import me.itaot.payment.cpcn.service.CpcnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/7/11 17:31
 * Email:itaot.me@gmail.com
 * Description:中金支付支付类接口
 */
@RestController
public class CpcnPaymentController {

    private static final Logger log = LoggerFactory.getLogger(CpcnPaymentController.class);

    @Autowired
    private CpcnService cpcnService;

    @Autowired
    private CpcnMesssageService cpcnMesssageService;

    /**
     * 中金支付:2011-单笔代扣接口
     */
    @RequestMapping(path = "/recharge", method = RequestMethod.POST)
    public String recharge(Map<String, String> paras) throws IOException {
        log.info("Start execute cpcn recharge services ... ");

        String cipherText = cpcnService.syncInterface("", "");

        log.debug("Response Message:" + cipherText);

        String plainText = null;
        try {
            plainText = cpcnMesssageService.parsePlainText(cipherText);
        } catch (PaymentException e) {
            e.printStackTrace();
        }

        log.info("plainText:" + plainText);
        log.info("End execute cpcn recharge services ... ");
        return plainText;
    }

    /**
     * 中金支付:2020-单笔代扣交易查询
     */
    @RequestMapping(path = "/query", method = RequestMethod.GET)
    public void query() {

    }

}