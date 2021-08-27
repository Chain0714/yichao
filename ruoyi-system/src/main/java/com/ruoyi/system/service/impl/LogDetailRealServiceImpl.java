package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.LogDetailRealMapper;
import com.ruoyi.system.domain.LogDetailReal;
import com.ruoyi.system.service.ILogDetailRealService;

/**
 * 实时数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-27
 */
@Service
public class LogDetailRealServiceImpl implements ILogDetailRealService 
{
    @Autowired
    private LogDetailRealMapper logDetailRealMapper;

    /**
     * 查询实时数据
     * 
     * @param id 实时数据主键
     * @return 实时数据
     */
    @Override
    public LogDetailReal selectLogDetailRealById(Long id)
    {
        return logDetailRealMapper.selectLogDetailRealById(id);
    }

    /**
     * 查询实时数据列表
     * 
     * @param logDetailReal 实时数据
     * @return 实时数据
     */
    @Override
    public List<LogDetailReal> selectLogDetailRealList(LogDetailReal logDetailReal)
    {
        return logDetailRealMapper.selectLogDetailRealList(logDetailReal);
    }

    /**
     * 新增实时数据
     * 
     * @param logDetailReal 实时数据
     * @return 结果
     */
    @Override
    public int insertLogDetailReal(LogDetailReal logDetailReal)
    {
        return logDetailRealMapper.insertLogDetailReal(logDetailReal);
    }

    /**
     * 修改实时数据
     * 
     * @param logDetailReal 实时数据
     * @return 结果
     */
    @Override
    public int updateLogDetailReal(LogDetailReal logDetailReal)
    {
        return logDetailRealMapper.updateLogDetailReal(logDetailReal);
    }

    /**
     * 批量删除实时数据
     * 
     * @param ids 需要删除的实时数据主键
     * @return 结果
     */
    @Override
    public int deleteLogDetailRealByIds(Long[] ids)
    {
        return logDetailRealMapper.deleteLogDetailRealByIds(ids);
    }

    /**
     * 删除实时数据信息
     * 
     * @param id 实时数据主键
     * @return 结果
     */
    @Override
    public int deleteLogDetailRealById(Long id)
    {
        return logDetailRealMapper.deleteLogDetailRealById(id);
    }
}
