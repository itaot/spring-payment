package me.itaot.payment.common.beans;

public class InstructMappingInf {

    /**
     * 系统代码
     */
    private String sysCode;
    /**
     * 指令类型
     */
    private String subSysCode;
    /**
     * 指令代码
     */
    private String instructCode;
    /**
     * 源属性
     */
    private String srcField;
    /**
     * 目标属性
     */
    private String destField;
    /**
     * 字段类型
     */
    private String type;
    /**
     * 字段长度
     */
    private String length;
    /**
     * 是否必输
     */
    private boolean must;
    /**
     * 备注
     */
    private String remark;

    private String defaultValue;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSubSysCode() {
        return subSysCode;
    }

    public void setSubSysCode(String subSysCode) {
        this.subSysCode = subSysCode;
    }

    public String getInstructCode() {
        return instructCode;
    }

    public void setInstructCode(String instructCode) {
        this.instructCode = instructCode;
    }

    public String getSrcField() {
        return srcField;
    }

    public void setSrcField(String srcField) {
        this.srcField = srcField;
    }

    public String getDestField() {
        return destField;
    }

    public void setDestField(String destField) {
        this.destField = destField;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public boolean isMust() {
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDefaultValue() {
        return null == defaultValue ? "" : defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return "[" + sysCode + "-" + subSysCode + "-" + instructCode + "]" + srcField + "->" + destField + "\t(type)" + type + "(length)" + length + "(must)" + must + "(remark)" + remark;
    }
}
