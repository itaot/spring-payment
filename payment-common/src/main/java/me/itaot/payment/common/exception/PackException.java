package me.itaot.payment.common.exception;

/**
 * Author:itaot
 * CreateTime:2018/7/16 20:13
 * Email:itaot.me@gmail.com
 * Description:组包异常类
 */
public class PackException extends Throwable {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PackException(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

}
