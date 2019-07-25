package me.itaot.payment.front.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author:itaot
 * CreateTime:2018/7/7 20:54
 * Email:itaot.me@gmail.com
 * Description:商户信息
 */
@Entity
@Table(name = "sp_merchant_info", schema = "front")
public class MerchantInfo {

    /**
     * 商户号
     */
    @Id
    private String merId;
    /**
     * 商户名称
     */
    private String merName;

    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 状态 0-正常 9-注销
     */
    private String status;
    /**
     * 签名类型 1、HMAC-SHA256 2、MD5
     */
    private String signType;
    /**
     * 签名key
     */
    private String key;
    /***
     * 注销时间
     */
    private Date logoutTime;
    /***
     * 证件类型
     */
    private String documentType;
    /***
     * 证件号码
     */
    private String documentId;

    /**
     * 权限,0:无权限 1:有权限  依次为:代付、代扣
     */
    private String privileges;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }
}