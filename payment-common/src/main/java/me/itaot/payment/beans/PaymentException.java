package me.itaot.payment.beans;

/**
 * Author:itaot
 * CreateTime:2018/7/8 16:40
 * Email:itaot.me@gmail.com
 * Description:支付系统自定义异常
 */
public class PaymentException extends Exception {

    private String moduleId;
    private String errorCode;
    private String errorMessage;

    public PaymentException() {

    }


    public PaymentException(String errorCode) {
        this.setErrorCode(errorCode);
    }

    public PaymentException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }


    @Override
    public String toString() {
        return "moudle[" + moduleId + "] \t 【" + errorCode + "】" + errorMessage + "\n " + this.getStackTrace();
    }
}
