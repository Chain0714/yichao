package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实时数据对象 log_detail_real
 * 
 * @author ruoyi
 * @date 2021-08-27
 */
public class LogDetailReal extends BaseEntity
{

    public LogDetailReal(String mn, String factorCode, String factorVal, Date dataTime) {
        this.mn = mn;
        this.factorCode = factorCode;
        this.factorVal = factorVal;
        this.dataTime = dataTime;
    }

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** mn码 */
    @Excel(name = "mn码")
    private String mn;

    /** 因子编码 */
    @Excel(name = "因子编码")
    private String factorCode;

    /** 因子值 */
    @Excel(name = "因子值")
    private String factorVal;

    /** 数据时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "数据时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dataTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMn(String mn) 
    {
        this.mn = mn;
    }

    public String getMn() 
    {
        return mn;
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

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }
}
