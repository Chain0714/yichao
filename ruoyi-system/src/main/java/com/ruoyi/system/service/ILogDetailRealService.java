package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.LogDetailReal;

/**
 * 实时数据Service接口
 * 
 * @author ruoyi
 * @date 2021-08-27
 */
public interface ILogDetailRealService 
{
    /**
     * 查询实时数据
     * 
     * @param id 实时数据主键
     * @return 实时数据
     */
    public LogDetailReal selectLogDetailRealById(Long id);

    /**
     * 查询实时数据列表
     * 
     * @param logDetailReal 实时数据
     * @return 实时数据集合
     */
    public List<LogDetailReal> selectLogDetailRealList(LogDetailReal logDetailReal);

    /**
     * 新增实时数据
     * 
     * @param logDetailReal 实时数据
     * @return 结果
     */
    public int insertLogDetailReal(LogDetailReal logDetailReal);

    /**
     * 修改实时数据
     * 
     * @param logDetailReal 实时数据
     * @return 结果
     */
    public int updateLogDetailReal(LogDetailReal logDetailReal);

    /**
     * 批量删除实时数据
     * 
     * @param ids 需要删除的实时数据主键集合
     * @return 结果
     */
    public int deleteLogDetailRealByIds(Long[] ids);

    /**
     * 删除实时数据信息
     * 
     * @param id 实时数据主键
     * @return 结果
     */
    public int deleteLogDetailRealById(Long id);
}
