package me.itaot.payment.service.pack.bean;


/**
 * Author:itaot
 * CreateTime:2018/8/7 22:09
 * Email:itaot.me@gmail.com
 * Description:
 */
public class StringField extends Field {
    public StringField(String field, String value) {
        setType("string");
        setField(field);
        setValue(value);
        setLength(value.length());
    }
}