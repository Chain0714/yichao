package com.ruoyi.system.domain.dto;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

public class DataHistoryDto implements Serializable {
    private static final long serialVersionUID = 5170241899409063905L;

    /**
     * 数据类型
     */
    @Excel(name = "数据类型")
    private String cn;

    private String mn;

    /**
     * 设备编码
     */
    @Excel(name = "采集点")
    private String pointName;


    @Excel(name = "因子编码")
    private String factorCode;

    @Excel(name = "因子名称")
    private String factorName;

    @Excel(name = "因子值")
    private String factorVal;

    /**
     * 数据时间
     */
    @Excel(name = "数据时间")
    private String dataTime;

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public String getFactorVal() {
        return factorVal;
    }

    public void setFactorVal(String factorVal) {
        this.factorVal = factorVal;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String groupKey() {
        return this.pointName + "|" + this.mn;
    }
}
