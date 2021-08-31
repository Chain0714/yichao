package com.ruoyi.web.controller.pullution;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.system.domain.dto.DataHistoryDto;
import com.ruoyi.system.service.IHjParseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.DataLog;
import com.ruoyi.system.service.IDataLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 历史查询Controller
 *
 * @author ruoyi
 * @date 2021-08-26
 */
@RestController
@RequestMapping("/pullution/history")
public class DataLogController extends BaseController {
    @Autowired
    private IDataLogService dataLogService;

    @Autowired
    private IHjParseService hjParseService;


    /**
     * 查询历史查询列表
     */
    @DataScope(deptAlias = "sd")
    @PreAuthorize("@ss.hasPermi('pullution:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(DataLog dataLog) {
        startPage();
        List<DataHistoryDto> list = dataLogService.selectDataLogList(dataLog);
        return getDataTable(list);
    }

    @DataScope(deptAlias = "sd")
    @PreAuthorize("@ss.hasPermi('pullution:real:list')")
    @GetMapping("/listReal")
    public Map<String, List<DataHistoryDto>> listReal(DataLog dataLog) {
        return dataLogService.selectDataLogReal(dataLog);
    }

    /**
     * 导出历史查询列表
     */
    @DataScope(deptAlias = "sd")
    @PreAuthorize("@ss.hasPermi('pullution:history:export')")
    @Log(title = "历史查询", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataLog dataLog) {
        List<DataHistoryDto> list = dataLogService.selectDataLogList(dataLog);
        ExcelUtil<DataHistoryDto> util = new ExcelUtil<DataHistoryDto>(DataHistoryDto.class);
        return util.exportExcel(list, "历史查询数据");
    }

}
