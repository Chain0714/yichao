package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DataLog;
import com.ruoyi.system.domain.LogDetail;
import com.ruoyi.system.domain.LogDetailReal;
import com.ruoyi.system.domain.dto.DataHistoryDto;
import org.apache.ibatis.annotations.Param;

/**
 * 历史查询Mapper接口
 * 
 * @author ruoyi
 * @date 2021-08-26
 */
public interface DataLogMapper 
{
    /**
     * 查询历史查询
     * 
     * @param id 历史查询主键
     * @return 历史查询
     */
    public DataLog selectDataLogById(Long id);

    /**
     * 查询历史查询列表
     * 
     * @param dataLog 历史查询
     * @return 历史查询集合
     */
    public List<DataHistoryDto> selectDataLogList(DataLog dataLog);

    List<DataHistoryDto> selectLogReal(String mn);

    /**
     * 新增历史查询
     * 
     * @param dataLog 历史查询
     * @return 结果
     */
    public int insertDataLog(DataLog dataLog);

    /**
     * 修改历史查询
     * 
     * @param dataLog 历史查询
     * @return 结果
     */
    public int updateDataLog(DataLog dataLog);

    /**
     * 删除历史查询
     * 
     * @param id 历史查询主键
     * @return 结果
     */
    public int deleteDataLogById(Long id);

    /**
     * 批量删除历史查询
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataLogByIds(Long[] ids);

    /**
     * 批量删除采集日志详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLogDetailByIds(Long[] ids);
    
    /**
     * 批量新增采集日志详情
     * 
     * @param logDetailList 采集日志详情列表
     * @return 结果
     */
    public int batchLogDetail(List<LogDetail> logDetailList);

    /**
     * 批量新增采集日志详情
     *
     * @param logDetailList 采集日志详情列表
     * @return 结果
     */
    public int batchLogDetailReal(List<LogDetailReal> logDetailList);

    public int deleteLogDetailByMn(String mn);
    

    /**
     * 通过历史查询主键删除采集日志详情信息
     * 
     * @param id 历史查询ID
     * @return 结果
     */
    public int deleteLogDetailByLogId(Long id);
}
