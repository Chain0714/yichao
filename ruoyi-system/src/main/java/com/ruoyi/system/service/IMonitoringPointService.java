package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MonitoringPoint;

/**
 * 监测点信息Service接口
 * 
 * @author ruoyi
 * @date 2021-08-24
 */
public interface IMonitoringPointService 
{
    /**
     * 查询监测点信息
     * 
     * @param id 监测点信息主键
     * @return 监测点信息
     */
    public MonitoringPoint selectMonitoringPointById(Long id);

    /**
     * 查询监测点信息列表
     * 
     * @param monitoringPoint 监测点信息
     * @return 监测点信息集合
     */
    public List<MonitoringPoint> selectMonitoringPointList(MonitoringPoint monitoringPoint);

    /**
     * 新增监测点信息
     * 
     * @param monitoringPoint 监测点信息
     * @return 结果
     */
    public int insertMonitoringPoint(MonitoringPoint monitoringPoint);

    /**
     * 修改监测点信息
     * 
     * @param monitoringPoint 监测点信息
     * @return 结果
     */
    public int updateMonitoringPoint(MonitoringPoint monitoringPoint);

    /**
     * 批量删除监测点信息
     * 
     * @param ids 需要删除的监测点信息主键集合
     * @return 结果
     */
    public int deleteMonitoringPointByIds(Long[] ids);

    /**
     * 删除监测点信息信息
     * 
     * @param id 监测点信息主键
     * @return 结果
     */
    public int deleteMonitoringPointById(Long id);
}
