package com.ruoyi.web.controller.pullution;

import java.util.List;
import java.util.Map;

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
    @PreAuthorize("@ss.hasPermi('pullution:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(DataLog dataLog) {
        startPage();
        List<DataHistoryDto> list = dataLogService.selectDataLogList(dataLog);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('pullution:real:list')")
    @GetMapping("/listReal")
    public Map<String, List<DataHistoryDto>> listReal(DataLog dataLog) {
        return dataLogService.selectDataLogReal(dataLog.getMn());
    }

    /**
     * 导出历史查询列表
     */
    @PreAuthorize("@ss.hasPermi('pullution:history:export')")
    @Log(title = "历史查询", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataLog dataLog) {
//        String msg1 = "##0209ST=32;CN=2011;PW=123456;MN=88888811111006;CP=&&DataTime=20210824121000;B01-Min=0.00,B01-Avg=3.82,B01-Max=9.76,B01-Cou=0;001-Min=7.62,001-Avg=7.64,001-Max=7.65;011-Min=17.2,011-Avg=17.2,011-Max=17.2,011-Cou=0&&D341\r\n";
//        String msg2 = "##0222QN=20210824122004007;ST=32;CN=2011;PW=123456;MN=88888811111008;Flag=4;CP=&&DataTime=20210824122000;w00000-Rtd=0.00,w00000-Flag=N;w01001-Rtd=8.43,w01001-Flag=N;w01018-Rtd=7.0,w01018-SampleTime=20210824103659,w01018-Flag=N&&3840\r\n";
//        hjParseService.process(msg1);
//        hjParseService.process(msg2);
        List<DataHistoryDto> list = dataLogService.selectDataLogList(dataLog);
        ExcelUtil<DataHistoryDto> util = new ExcelUtil<DataHistoryDto>(DataHistoryDto.class);
        return util.exportExcel(list, "历史查询数据");
    }

}
