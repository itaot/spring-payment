package me.itaot.payment.front.services.impl;

import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.front.beans.FrontException;
import me.itaot.payment.front.services.PaymentSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/7/8 12:51
 * Email:itaot.me@gmail.com
 * Description:加签验签实现类
 */
@Service
public class PaymentSecurityServiceImpl implements PaymentSecurityService {

    private static final Logger log = LoggerFactory.getLogger(PaymentSecurityServiceImpl.class);


    @Override
    public Map<String, String> parsePlaintext(String plainText) throws FrontException {
        log.info("Start execute setPlaintext2Request ... ");
        log.debug("plainText=>" + plainText);

        Map<String, String> requestParas = new HashMap<String, String>();
        for (String kvStr : plainText.split("&")) {
            String[] kv = kvStr.split("=");
            try {
                requestParas.put(kv[0], URLDecoder.decode(kv[1], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                log.error("【服务器内部异常】不支持的服务器编码");
                throw new FrontException("F00001");
            }
        }

        log.info("End execute raw verify ...");
        return requestParas;
    }

    @Override
    public boolean rawVerify(Map<String, String> kvs) {
        return false;
    }
}