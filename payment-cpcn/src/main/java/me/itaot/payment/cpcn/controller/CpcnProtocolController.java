package me.itaot.payment.cpcn.controller;

import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.common.exception.PackException;
import me.itaot.payment.cpcn.service.CpcnMesssageService;
import me.itaot.payment.cpcn.service.CpcnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:itaot
 * CreateTime:2018/7/16 13:44
 * Email:itaot.me@gmail.com
 * Description:中金支付协议类接口
 */
@RestController
public class CpcnProtocolController {

    private static final Logger log = LoggerFactory.getLogger(CpcnProtocolController.class);

    private static final String SYS_CODE = "CPCN";
    private static final String SUB_SYS_CODE = "XYZF";
    private static final String TRAN_ID_SIGN_APPLY = "2531";

    @Autowired
    private CpcnService cpcnService;

    @Autowired
    private CpcnMesssageService cpcnMessageService;

    /**
     * 中金支付:2531 – 建立绑定关系申请（发送验证码）
     */
    @RequestMapping(path = "/protocol", method = RequestMethod.POST)
    public Map<String, String> signApply(@RequestParam Map<String, String> paras) {
        log.info("Start execute cpcn prototol apply services ... ");
        Map<String, String> response = new HashMap<String, String>();
        long startTime = System.currentTimeMillis();

        String plainText = "";
        String cipherText = "";
        String signature = "";

        //1、生成流水号
        String msgSeqNo = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "0000000001";
        paras.put("MSG_SEQ_NO", msgSeqNo);

        try {
            plainText = cpcnMessageService.pack(SYS_CODE, SUB_SYS_CODE, "R" + TRAN_ID_SIGN_APPLY, paras);
        } catch (PackException e) {
            log.error("组包异常{}", e);
        }

        //2、组织请求报文
        Map<String, String> requestParas = cpcnMessageService.orgRequestParas(plainText);
        log.debug("中金请求报文:{}", requestParas);

        //3、请求中金
        String responseText = cpcnService.syncInterface(requestParas.get("message"), requestParas.get("signature"));
        log.debug("Response Message:" + responseText);

        //4、解析响应报文、验签
        String responsePlainText = "";
        try {
            responsePlainText = cpcnMessageService.parsePlainText(responseText);
        } catch (PaymentException e) {
            responsePlainText = "验签失败";
        }
        log.info("plainText:" + responsePlainText);

        //5、响应报文解析
        response = cpcnMessageService.unpack(SYS_CODE, SUB_SYS_CODE, "I" + TRAN_ID_SIGN_APPLY, responsePlainText);

        long endTime = System.currentTimeMillis();
        log.info("End execute cpcn protocol apply service,耗时{}ms", (endTime - startTime));
        return response;
    }

}
