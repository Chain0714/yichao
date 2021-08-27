package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.LogDetailReal;
import com.ruoyi.system.domain.dto.DataHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.LogDetail;
import com.ruoyi.system.mapper.DataLogMapper;
import com.ruoyi.system.domain.DataLog;
import com.ruoyi.system.service.IDataLogService;

/**
 * 历史查询Service业务层处理
 *
 * @author ruoyi
 * @date 2021-08-26
 */
@Service
public class DataLogServiceImpl implements IDataLogService {
    @Autowired
    private DataLogMapper dataLogMapper;

    /**
     * 查询历史查询
     *
     * @param id 历史查询主键
     * @return 历史查询
     */
    @Override
    public DataLog selectDataLogById(Long id) {
        return dataLogMapper.selectDataLogById(id);
    }

    /**
     * 查询历史查询列表
     *
     * @param dataLog 历史查询
     * @return 历史查询
     */
    @Override
    public List<DataHistoryDto> selectDataLogList(DataLog dataLog) {

        return dataLogMapper.selectDataLogList(dataLog);
    }

    @Override
    public Map<String, List<DataHistoryDto>> selectDataLogReal(String mn) {

        List<DataHistoryDto> list = dataLogMapper.selectLogReal(mn);
        return list.stream().collect(Collectors.groupingBy(DataHistoryDto::groupKey));

    }

    /**
     * 新增历史查询
     *
     * @param dataLog 历史查询
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDataLog(DataLog dataLog) {
        int rows = dataLogMapper.insertDataLog(dataLog);
        insertLogDetail(dataLog);
        return rows;
    }

    /**
     * 修改历史查询
     *
     * @param dataLog 历史查询
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDataLog(DataLog dataLog) {
        dataLogMapper.deleteLogDetailByLogId(dataLog.getId());
        insertLogDetail(dataLog);
        return dataLogMapper.updateDataLog(dataLog);
    }

    /**
     * 批量删除历史查询
     *
     * @param ids 需要删除的历史查询主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDataLogByIds(Long[] ids) {
//        dataLogMapper.deleteLogDetailByLogIds(ids);
        return dataLogMapper.deleteDataLogByIds(ids);
    }

    /**
     * 删除历史查询信息
     *
     * @param id 历史查询主键
     * @return 结果
     */
    @Override
    public int deleteDataLogById(Long id) {
        dataLogMapper.deleteLogDetailByLogId(id);
        return dataLogMapper.deleteDataLogById(id);
    }

    @Override
    public void insertReal(String mn, List<LogDetailReal> list) {
        dataLogMapper.deleteLogDetailByMn(mn);
        dataLogMapper.batchLogDetailReal(list);
    }

    /**
     * 新增采集日志详情信息
     *
     * @param dataLog 历史查询对象
     */
    public void insertLogDetail(DataLog dataLog) {
        List<LogDetail> logDetailList = dataLog.getLogDetailList();
        Long id = dataLog.getId();
        if (StringUtils.isNotNull(logDetailList)) {
            List<LogDetail> list = new ArrayList<LogDetail>();
            for (LogDetail logDetail : logDetailList) {
                logDetail.setLogId(id);
                list.add(logDetail);
            }
            if (list.size() > 0) {
                dataLogMapper.batchLogDetail(list);
            }
        }
    }
}
