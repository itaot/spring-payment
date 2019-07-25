package me.itaot.payment.service.pack.bean;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Author:itaot
 * CreateTime:2018/8/7 22:08
 * Email:itaot.me@gmail.com
 * Description:
 */
public class Field {
    private String field;
    private String value;
    private String scale = "0";
    private String type = "string";
    private int length;

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public String getScale() {
        return scale;
    }

    public String getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Element toElement() {
        Element fieldElement = DocumentHelper.createElement("data");
        fieldElement.addAttribute("name", field);

        Element valueElement = DocumentHelper.createElement("field");
        valueElement.addAttribute("length", "" + length);
        valueElement.addAttribute("scale", scale);
        valueElement.addAttribute("type", type);
        valueElement.setText(value);

        fieldElement.add(valueElement);
        return fieldElement;
    }
}
