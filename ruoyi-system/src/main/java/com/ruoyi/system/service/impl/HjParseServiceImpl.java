package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.DataLog;
import com.ruoyi.system.domain.LogDetail;
import com.ruoyi.system.domain.LogDetailReal;
import com.ruoyi.system.mapper.DataLogMapper;
import com.ruoyi.system.service.IDataLogService;
import com.ruoyi.system.service.IHjParseService;
import com.xy.format.hbt212.core.T212Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HjParseServiceImpl implements IHjParseService {

    private static final Logger log = LoggerFactory.getLogger(HjParseServiceImpl.class);


    @Autowired
    private IDataLogService dataLogService;

    @Autowired
    private DataLogMapper dataLogMapper;

    @Override
    public void process(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures();
        try {
            Map<String, Object> data = mapper.readDeepMap(s);
            Map<String, String> cp = (Map<String, String>) data.get("CP");
            //实时数据
            if ("2011".equals(data.get("CN"))) {
                Date dataTime = sdf.parse((((Map<String, String>) data.get("CP")).get("DataTime") + ""));
                String mn = data.get("MN") + "";
                List<LogDetailReal> list = new ArrayList<>();

                cp.forEach((k, v) -> {
                    if (!"DataTime".equals(k)) {
                        list.add(new LogDetailReal(mn, k, v, dataTime));
                    }
                });
                dataLogMapper.deleteLogDetailByMn(mn);
                dataLogMapper.batchLogDetailReal(list);


            } else {
                DataLog dataLog = new DataLog();
                dataLog.setSt(data.get("ST") + "");
                dataLog.setCn(data.get("CN") + "");
                dataLog.setMn(data.get("MN") + "");
                dataLog.setDataTime(sdf.parse((((Map<String, String>) data.get("CP")).get("DataTime") + "")));

                List<LogDetail> list = new ArrayList<>();

                cp.forEach((k, v) -> {
                    if (!"DataTime".equals(k)) {
                        list.add(new LogDetail(k, v));
                    }
                });
                dataLog.setLogDetailList(list);
                dataLogService.insertDataLog(dataLog);
            }

        } catch (Exception e) {
            log.error("采集数据记录失败", e);

        }
    }
}