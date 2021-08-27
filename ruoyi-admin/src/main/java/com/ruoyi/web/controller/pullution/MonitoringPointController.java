package com.ruoyi.web.controller.pullution;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.MonitoringPoint;
import com.ruoyi.system.service.IMonitoringPointService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 监测点信息Controller
 * 
 * @author ruoyi
 * @date 2021-08-24
 */
@RestController
@RequestMapping("/pullution/point")
public class MonitoringPointController extends BaseController
{
    @Autowired
    private IMonitoringPointService monitoringPointService;

    /**
     * 查询监测点信息列表
     */
    @PreAuthorize("@ss.hasPermi('pullution:point:list')")
    @GetMapping("/list")
    public TableDataInfo list(MonitoringPoint monitoringPoint)
    {
        startPage();
        List<MonitoringPoint> list = monitoringPointService.selectMonitoringPointList(monitoringPoint);
        return getDataTable(list);
    }

    /**
     * 导出监测点信息列表
     */
    @PreAuthorize("@ss.hasPermi('pullution:point:export')")
    @Log(title = "监测点信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MonitoringPoint monitoringPoint)
    {
        List<MonitoringPoint> list = monitoringPointService.selectMonitoringPointList(monitoringPoint);
        ExcelUtil<MonitoringPoint> util = new ExcelUtil<MonitoringPoint>(MonitoringPoint.class);
        return util.exportExcel(list, "监测点信息数据");
    }

    /**
     * 获取监测点信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('pullution:point:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(monitoringPointService.selectMonitoringPointById(id));
    }

    /**
     * 新增监测点信息
     */
    @PreAuthorize("@ss.hasPermi('pullution:point:add')")
    @Log(title = "监测点信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MonitoringPoint monitoringPoint)
    {
        return toAjax(monitoringPointService.insertMonitoringPoint(monitoringPoint));
    }

    /**
     * 修改监测点信息
     */
    @PreAuthorize("@ss.hasPermi('pullution:point:edit')")
    @Log(title = "监测点信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MonitoringPoint monitoringPoint)
    {
        return toAjax(monitoringPointService.updateMonitoringPoint(monitoringPoint));
    }

    /**
     * 删除监测点信息
     */
    @PreAuthorize("@ss.hasPermi('pullution:point:remove')")
    @Log(title = "监测点信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(monitoringPointService.deleteMonitoringPointByIds(ids));
    }
}
