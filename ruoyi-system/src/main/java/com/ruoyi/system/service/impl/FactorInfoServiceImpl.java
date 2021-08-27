package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FactorInfoMapper;
import com.ruoyi.system.domain.FactorInfo;
import com.ruoyi.system.service.IFactorInfoService;

/**
 * 因子信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-24
 */
@Service
public class FactorInfoServiceImpl implements IFactorInfoService 
{
    @Autowired
    private FactorInfoMapper factorInfoMapper;

    /**
     * 查询因子信息
     * 
     * @param id 因子信息主键
     * @return 因子信息
     */
    @Override
    public FactorInfo selectFactorInfoById(Long id)
    {
        return factorInfoMapper.selectFactorInfoById(id);
    }

    /**
     * 查询因子信息列表
     * 
     * @param factorInfo 因子信息
     * @return 因子信息
     */
    @Override
    public List<FactorInfo> selectFactorInfoList(FactorInfo factorInfo)
    {
        return factorInfoMapper.selectFactorInfoList(factorInfo);
    }

    /**
     * 新增因子信息
     * 
     * @param factorInfo 因子信息
     * @return 结果
     */
    @Override
    public int insertFactorInfo(FactorInfo factorInfo)
    {
        return factorInfoMapper.insertFactorInfo(factorInfo);
    }

    /**
     * 修改因子信息
     * 
     * @param factorInfo 因子信息
     * @return 结果
     */
    @Override
    public int updateFactorInfo(FactorInfo factorInfo)
    {
        return factorInfoMapper.updateFactorInfo(factorInfo);
    }

    /**
     * 批量删除因子信息
     * 
     * @param ids 需要删除的因子信息主键
     * @return 结果
     */
    @Override
    public int deleteFactorInfoByIds(Long[] ids)
    {
        return factorInfoMapper.deleteFactorInfoByIds(ids);
    }

    /**
     * 删除因子信息信息
     * 
     * @param id 因子信息主键
     * @return 结果
     */
    @Override
    public int deleteFactorInfoById(Long id)
    {
        return factorInfoMapper.deleteFactorInfoById(id);
    }
}
