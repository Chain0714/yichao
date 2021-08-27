package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FactorInfo;

/**
 * 因子信息Service接口
 * 
 * @author ruoyi
 * @date 2021-08-24
 */
public interface IFactorInfoService 
{
    /**
     * 查询因子信息
     * 
     * @param id 因子信息主键
     * @return 因子信息
     */
    public FactorInfo selectFactorInfoById(Long id);

    /**
     * 查询因子信息列表
     * 
     * @param factorInfo 因子信息
     * @return 因子信息集合
     */
    public List<FactorInfo> selectFactorInfoList(FactorInfo factorInfo);

    /**
     * 新增因子信息
     * 
     * @param factorInfo 因子信息
     * @return 结果
     */
    public int insertFactorInfo(FactorInfo factorInfo);

    /**
     * 修改因子信息
     * 
     * @param factorInfo 因子信息
     * @return 结果
     */
    public int updateFactorInfo(FactorInfo factorInfo);

    /**
     * 批量删除因子信息
     * 
     * @param ids 需要删除的因子信息主键集合
     * @return 结果
     */
    public int deleteFactorInfoByIds(Long[] ids);

    /**
     * 删除因子信息信息
     * 
     * @param id 因子信息主键
     * @return 结果
     */
    public int deleteFactorInfoById(Long id);
}
