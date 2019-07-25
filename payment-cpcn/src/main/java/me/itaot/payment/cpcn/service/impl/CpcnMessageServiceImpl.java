package me.itaot.payment.cpcn.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sun.jersey.core.util.Base64;
import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.common.exception.PackException;
import me.itaot.payment.cpcn.cpcn.SignatureFactory;
import me.itaot.payment.cpcn.service.CpcnInstructMappingService;
import me.itaot.payment.cpcn.service.CpcnMesssageService;
import me.itaot.payment.cpcn.service.CpcnSecurityService;
import me.itaot.payment.service.message.UniformPackService;
import me.itaot.payment.service.message.UniformParserService;
import me.itaot.payment.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/7/13 10:31
 * Email:itaot.me@gmail.com
 * Description:中金报文服务
 */
@Service
public class CpcnMessageServiceImpl implements CpcnMesssageService {

    private static final Logger log = LoggerFactory.getLogger(CpcnMessageServiceImpl.class);

    @Autowired
    private CpcnSecurityService cpcnSecurityService;

    @Autowired
    private CpcnInstructMappingService cpcnInstructMappingService;


    @Reference
    private UniformPackService packService;

    @Reference
    private UniformParserService unpackService;


    @Override
    public String parsePlainText(String cipherText) throws PaymentException {
        int commaIndex = cipherText.indexOf(",");
        String message = cipherText.substring(0, commaIndex).trim();
        String signature = cipherText.substring(commaIndex + 1).trim();
        String plainText = "";
        log.debug("cipherMessage:" + message);
        log.debug("signature:" + signature);

        //1、解码响应报文
        byte[] decodeStr = null;
        try {
            decodeStr = new BASE64Decoder().decodeBuffer(message);
            plainText = new String(decodeStr, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2、验签
        boolean verifySuccess = cpcnSecurityService.verify(plainText, signature);

        if (!verifySuccess) {
            throw new PaymentException("S00001", "验签失败，不做后续处理...");
        }


        //3、返回明文报文
        return plainText;

    }

    @Override
    public String pack(String sysCode, String subSysCode, String msgCode, Map<String, String> data) throws PackException {
        String tranId = sysCode + "-" + subSysCode + "-" + msgCode;
        log.debug("Start execute pack:{}", tranId);
        log.debug("Pack data:{}", data);
        String message = packService.pack(tranId, data);

        if (!message.contains("standalone=\"no\"")) {
            message = message.replace("encoding=\"UTF-8\"?", "encoding=\"UTF-8\" standalone=\"no\"?");
        }

        log.debug("组织的中金报文为:{}", message);

        return message;
    }

    @Override
    public Map<String, String> orgRequestParas(String plainText) {

        Map<String, String> requestPara = new HashMap<String, String>();

        //1、Base64转码
        String cipherText = "";
        try {

            byte[] bts = new Base64().encode(plainText.getBytes("UTF-8"));
            cipherText = new String(bts);

            log.debug("message:{}", cipherText);
            requestPara.put("message", cipherText);
        } catch (UnsupportedEncodingException e) {
            log.error("message加密异常,{}", e);
            e.printStackTrace();
        }

        //2、加签
        try {
            byte[] signatureBytes = SignatureFactory.getSigner().sign(plainText.getBytes());
            String signature = StringUtil.bytes2hex(signatureBytes);
            log.debug("signature:{}", signature);
            requestPara.put("signature", signature);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requestPara;
    }

    @Override
    public Map<String, String> unpack(String sysCode, String subSysCode, String msgCode, String message) {

        Map<String, String> response = null;

        String tranId = sysCode + "-" + subSysCode + "-" + msgCode;
        response = unpackService.unpack(tranId, message);

        log.debug("拆包结果:{}", response);

        return response;
    }


}