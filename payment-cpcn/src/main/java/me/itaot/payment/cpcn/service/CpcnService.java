package me.itaot.payment.cpcn.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Author:itaot
 * CreateTime:2018/7/12 9:12
 * Email:itaot.me@gmail.com
 * Description:中金接口服务调用
 */
@FeignClient(url = "${cpcn.domain}", name = "payment-cpcn")
public interface CpcnService {

    /**
     * 中金同步接口
     * 包含接口:2011、2020、2031、2040、1810、1811、9999
     */
    @RequestMapping(value = "${cpcn.sync.interface}", method = RequestMethod.POST)
    public String syncInterface(@RequestParam(value = "message", required = true) String message,
                                @RequestParam(value = "signature", required = true) String signature);

    /**
     * 中金异步接口
     * 包含接口：
     */
    @RequestMapping(value = "${cpcn.async.interface}", method = RequestMethod.POST)
    public void asyncInterface(@RequestParam(value = "message", required = true) String message,
                               @RequestParam(value = "signature", required = true) String signature);

}
