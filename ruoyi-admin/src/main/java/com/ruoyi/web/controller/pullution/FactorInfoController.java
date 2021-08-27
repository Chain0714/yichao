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
import com.ruoyi.system.domain.FactorInfo;
import com.ruoyi.system.service.IFactorInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 因子信息Controller
 * 
 * @author ruoyi
 * @date 2021-08-24
 */
@RestController
@RequestMapping("/pullution/factor")
public class FactorInfoController extends BaseController
{
    @Autowired
    private IFactorInfoService factorInfoService;

    /**
     * 查询因子信息列表
     */
    @PreAuthorize("@ss.hasPermi('pullution:factor:list')")
    @GetMapping("/list")
    public TableDataInfo list(FactorInfo factorInfo)
    {
        startPage();
        List<FactorInfo> list = factorInfoService.selectFactorInfoList(factorInfo);
        return getDataTable(list);
    }

    /**
     * 导出因子信息列表
     */
    @PreAuthorize("@ss.hasPermi('pullution:factor:export')")
    @Log(title = "因子信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FactorInfo factorInfo)
    {
        List<FactorInfo> list = factorInfoService.selectFactorInfoList(factorInfo);
        ExcelUtil<FactorInfo> util = new ExcelUtil<FactorInfo>(FactorInfo.class);
        return util.exportExcel(list, "因子信息数据");
    }

    /**
     * 获取因子信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('pullution:factor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(factorInfoService.selectFactorInfoById(id));
    }

    /**
     * 新增因子信息
     */
    @PreAuthorize("@ss.hasPermi('pullution:factor:add')")
    @Log(title = "因子信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FactorInfo factorInfo)
    {
        return toAjax(factorInfoService.insertFactorInfo(factorInfo));
    }

    /**
     * 修改因子信息
     */
    @PreAuthorize("@ss.hasPermi('pullution:factor:edit')")
    @Log(title = "因子信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FactorInfo factorInfo)
    {
        return toAjax(factorInfoService.updateFactorInfo(factorInfo));
    }

    /**
     * 删除因子信息
     */
    @PreAuthorize("@ss.hasPermi('pullution:factor:remove')")
    @Log(title = "因子信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(factorInfoService.deleteFactorInfoByIds(ids));
    }
}
