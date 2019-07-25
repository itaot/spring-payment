package me.itaot.payment.front.services.impl;

import me.itaot.payment.front.jpa.FrontExceptionJpa;
import me.itaot.payment.front.services.FrontExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:itaot
 * CreateTime:2018/7/8 16:49
 * Email:itaot.me@gmail.com
 * Description:
 */
@Service
public class FrontExceptionServiceImpl implements FrontExceptionService {

    @Autowired
    private FrontExceptionJpa frontExceptionJpa;

    @Override
    public String getErrorMessage(String errorCode) {
        return null;
    }
}
