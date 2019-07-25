package me.itaot.payment.router.beans;


public class FormatInfo {
    /**
     * 渠道名称
     */
    private String format;
    /**
     * 单笔限额
     */
    private String singleQuota;
    /**
     * 日累计限额
     */
    private String dailyQuota;
    /**
     * 日可用余额
     */
    private int usableQuota;

    /**
     * 手续费(每笔)
     */
    private int fee;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSingleQuota() {
        return singleQuota;
    }

    public void setSingleQuota(String singleQuota) {
        this.singleQuota = singleQuota;
    }

    public String getDailyQuota() {
        return dailyQuota;
    }

    public void setDailyQuota(String dailyQuota) {
        this.dailyQuota = dailyQuota;
    }

    public int getUsableQuota() {
        return usableQuota;
    }

    public void setUsableQuota(int usableQuota) {
        this.usableQuota = usableQuota;
    }
}
