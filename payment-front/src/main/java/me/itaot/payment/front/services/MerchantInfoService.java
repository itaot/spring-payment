package me.itaot.payment.front.services;

import me.itaot.payment.front.beans.MerchantInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:itaot
 * CreateTime:2018/7/7 20:59
 * Email:itaot.me@gmail.com
 * Description:
 */

public interface MerchantInfoService {

    public List<MerchantInfo> list(MerchantInfo merchantInfo);

}
