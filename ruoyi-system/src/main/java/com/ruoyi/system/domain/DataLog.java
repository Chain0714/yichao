package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 历史查询对象 data_log
 * 
 * @author ruoyi
 * @date 2021-08-26
 */
public class DataLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 系统类型 */
    private String st;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String cn;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String mn;

    /** 标志  */
    private Integer flag;

    /** 数据时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "数据时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dataTime;

    /** 采集日志详情信息 */
    private List<LogDetail> logDetailList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSt(String st) 
    {
        this.st = st;
    }

    public String getSt() 
    {
        return st;
    }
    public void setCn(String cn) 
    {
        this.cn = cn;
    }

    public String getCn() 
    {
        return cn;
    }
    public void setMn(String mn) 
    {
        this.mn = mn;
    }

    public String getMn() 
    {
        return mn;
    }
    public void setFlag(Integer flag) 
    {
        this.flag = flag;
    }

    public Integer getFlag() 
    {
        return flag;
    }
    public void setDataTime(Date dataTime) 
    {
        this.dataTime = dataTime;
    }

    public Date getDataTime() 
    {
        return dataTime;
    }

    public List<LogDetail> getLogDetailList()
    {
        return logDetailList;
    }

    public void setLogDetailList(List<LogDetail> logDetailList)
    {
        this.logDetailList = logDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("st", getSt())
            .append("cn", getCn())
            .append("mn", getMn())
            .append("flag", getFlag())
            .append("dataTime", getDataTime())
            .append("logDetailList", getLogDetailList())
            .toString();
    }
}
