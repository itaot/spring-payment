package me.itaot.payment.cpcn.service.impl;

import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.cpcn.cpcn.SignatureFactory;
import me.itaot.payment.cpcn.service.CpcnSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Author:itaot
 * CreateTime:2018/7/13 10:37
 * Email:itaot.me@gmail.com
 * Description:
 */
@Service
public class CpcnSecurityServiceImpl implements CpcnSecurityService {

    private static final Logger log = LoggerFactory.getLogger(CpcnSecurityServiceImpl.class);

    @Override
    public boolean verify(String msg, String signStr) throws PaymentException {
        boolean verifySuccess = false;
        try {
            verifySuccess = SignatureFactory.getVerifier().verify(msg, signStr);
        } catch (Exception e) {
            log.error("验签异常", e);
            throw new PaymentException("F00001", "验签异常");
        }

        log.info("中金支付来账验签结果:" + verifySuccess);

        return verifySuccess;
    }

}
