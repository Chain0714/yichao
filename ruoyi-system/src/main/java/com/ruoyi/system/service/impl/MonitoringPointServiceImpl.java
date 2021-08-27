package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MonitoringPointMapper;
import com.ruoyi.system.domain.MonitoringPoint;
import com.ruoyi.system.service.IMonitoringPointService;

/**
 * 监测点信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-24
 */
@Service
public class MonitoringPointServiceImpl implements IMonitoringPointService 
{
    @Autowired
    private MonitoringPointMapper monitoringPointMapper;

    /**
     * 查询监测点信息
     * 
     * @param id 监测点信息主键
     * @return 监测点信息
     */
    @Override
    public MonitoringPoint selectMonitoringPointById(Long id)
    {
        return monitoringPointMapper.selectMonitoringPointById(id);
    }

    /**
     * 查询监测点信息列表
     * 
     * @param monitoringPoint 监测点信息
     * @return 监测点信息
     */
    @Override
    public List<MonitoringPoint> selectMonitoringPointList(MonitoringPoint monitoringPoint)
    {
        return monitoringPointMapper.selectMonitoringPointList(monitoringPoint);
    }

    /**
     * 新增监测点信息
     * 
     * @param monitoringPoint 监测点信息
     * @return 结果
     */
    @Override
    public int insertMonitoringPoint(MonitoringPoint monitoringPoint)
    {
        return monitoringPointMapper.insertMonitoringPoint(monitoringPoint);
    }

    /**
     * 修改监测点信息
     * 
     * @param monitoringPoint 监测点信息
     * @return 结果
     */
    @Override
    public int updateMonitoringPoint(MonitoringPoint monitoringPoint)
    {
        return monitoringPointMapper.updateMonitoringPoint(monitoringPoint);
    }

    /**
     * 批量删除监测点信息
     * 
     * @param ids 需要删除的监测点信息主键
     * @return 结果
     */
    @Override
    public int deleteMonitoringPointByIds(Long[] ids)
    {
        return monitoringPointMapper.deleteMonitoringPointByIds(ids);
    }

    /**
     * 删除监测点信息信息
     * 
     * @param id 监测点信息主键
     * @return 结果
     */
    @Override
    public int deleteMonitoringPointById(Long id)
    {
        return monitoringPointMapper.deleteMonitoringPointById(id);
    }
}
