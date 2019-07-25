package me.itaot.payment.front.jpa;

import me.itaot.payment.front.beans.FrontException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author:itaot
 * CreateTime:2018/7/8 16:51
 * Email:itaot.me@gmail.com
 * Description:错误码-错误信息
 */
@Repository
public interface FrontExceptionJpa extends JpaRepository<FrontException, String> {

    public String getErrorMessageByErrorCode(String errorCode);

}
