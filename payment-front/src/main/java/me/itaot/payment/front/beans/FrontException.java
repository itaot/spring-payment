package me.itaot.payment.front.beans;

import me.itaot.payment.beans.PaymentException;
import me.itaot.payment.front.services.FrontExceptionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * Author:itaot
 * CreateTime:2018/7/8 16:40
 * Email:itaot.me@gmail.com
 * Description:支付系统自定义异常
 */
@Entity
@Table(name = "sp_error", schema = "front")
public class FrontException extends PaymentException {

    @Transient
    @Autowired
    FrontExceptionService frontExceptionService;

    @Id
    private String errorCode;

    private String errorMessage;

    public FrontException() {
    }

    public FrontException(String errorCode) {
        this.setErrorCode(errorCode);
    }

    public FrontException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        String errorMessage = frontExceptionService.getErrorMessage(errorCode);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
