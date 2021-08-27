package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采集日志详情对象 log_detail
 * 
 * @author ruoyi
 * @date 2021-08-26
 */
public class LogDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 因子编码 */
    @Excel(name = "因子编码")
    private String factorCode;

    /** 因子值 */
    @Excel(name = "因子值")
    private String factorVal;

    /** 采集日志id */
    private Long logId;

    public LogDetail(String factorCode, String factorVal) {
        this.factorCode = factorCode;
        this.factorVal = factorVal;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFactorCode(String factorCode) 
    {
        this.factorCode = factorCode;
    }

    public String getFactorCode() 
    {
        return factorCode;
    }
    public void setFactorVal(String factorVal) 
    {
        this.factorVal = factorVal;
    }

    public String getFactorVal() 
    {
        return factorVal;
    }
    public void setLogId(Long logId) 
    {
        this.logId = logId;
    }

    public Long getLogId() 
    {
        return logId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("factorCode", getFactorCode())
            .append("factorVal", getFactorVal())
            .append("logId", getLogId())
            .toString();
    }
}
