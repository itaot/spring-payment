package me.itaot.payment.front.services.impl;

import me.itaot.payment.front.beans.MerchantInfo;
import me.itaot.payment.front.jpa.MerchantInfoRepository;
import me.itaot.payment.front.services.MerchantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:itaot
 * CreateTime:2018/7/7 21:00
 * Email:itaot.me@gmail.com
 * Description:
 */
@Service
public class MerchantInfoServiceImpl implements MerchantInfoService {

    @Autowired
    private MerchantInfoRepository merchantInfoJpa;

    @Override
    public List<MerchantInfo> list(MerchantInfo merchantInfo) {
        //Sort sort = new Sort(Sort.Direction.ASC, "mer_id");
        //int pageSize = 10, pageIndex = 0;
        //Pageable pageable = new PageRequest(pageSize, pageIndex, sort);
        //return merchantInfoJpa.finaAll(merchantInfo,pageable);
        return merchantInfoJpa.findAll();
    }
}
