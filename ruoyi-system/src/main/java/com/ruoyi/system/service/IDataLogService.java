package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.DataLog;
import com.ruoyi.system.domain.LogDetailReal;
import com.ruoyi.system.domain.dto.DataHistoryDto;

/**
 * 历史查询Service接口
 * 
 * @author ruoyi
 * @date 2021-08-26
 */
public interface IDataLogService 
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

    Map<String, List<DataHistoryDto>> selectDataLogReal(DataLog mn);

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
     * 批量删除历史查询
     * 
     * @param ids 需要删除的历史查询主键集合
     * @return 结果
     */
    public int deleteDataLogByIds(Long[] ids);

    /**
     * 删除历史查询信息
     * 
     * @param id 历史查询主键
     * @return 结果
     */
    public int deleteDataLogById(Long id);

    void insertReal(String mn,List<LogDetailReal> list);

    void cleanHistory(String cn,Integer days);
}
