package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.system.domain.DataLog;
import com.ruoyi.system.domain.LogDetail;
import com.ruoyi.system.domain.LogDetailReal;
import com.ruoyi.system.mapper.DataLogMapper;
import com.ruoyi.system.service.IDataLogService;
import com.ruoyi.system.service.IHjParseService;
import com.xy.format.hbt212.core.T212Mapper;
import com.xy.format.hbt212.exception.T212FormatException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class HjParseServiceImpl implements IHjParseService {

    private static final Logger log = LoggerFactory.getLogger(HjParseServiceImpl.class);


    @Autowired
    private IDataLogService dataLogService;

    @Autowired
    private DataLogMapper dataLogMapper;

    @Autowired
    public StringRedisTemplate redisTemplate;

    private static final String EXCLUDE_ST_KEY = "exclude_st";
    private static final String MN_CACHE_PREFIX = "mn_cache_";

    @Override
    public void process(String msg) throws IOException, T212FormatException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures();
        Map<String, String> data = mapper.readMap(msg);
        if (exclude(data.get("ST"))) {
            log.info("exclude");
            return;
        }
        String cp = data.get("CP");
        String[] split = cp.split("[;,]");
        Map<String, String> cpMap = new HashMap<>();
        for (String s : split) {
            String[] sp = s.split("=");
            if (sp.length == 2) {
                cpMap.put(sp[0], sp[1]);
            }
        }
        //实时数据
        if ("2011".equals(data.get("CN"))) {
            Date dataTime = sdf.parse((cpMap.get("DataTime")));
            String mn = data.get("MN") + "";
            List<LogDetailReal> list = new ArrayList<>();

            cpMap.forEach((k, v) -> {
                if (!"DataTime".equals(k)) {
                    list.add(new LogDetailReal(mn, k, v, dataTime));
                }
            });
            dataLogMapper.deleteLogDetailByMn(mn);
            dataLogMapper.batchLogDetailReal(list);
        }
        DataLog dataLog = new DataLog();
        dataLog.setSt(data.get("ST") + "");
        dataLog.setCn(data.get("CN") + "");
        dataLog.setMn(data.get("MN") + "");

        if (!checkKey(data.get("ST") + "", data.get("CN") + "", data.get("MN") + "")) {
            log.info("十分钟以内只记录1条数据,结束");
            return;
        }

        dataLog.setDataTime(sdf.parse((cpMap.get("DataTime") + "")));

        List<LogDetail> list = new ArrayList<>();

        cpMap.forEach((k, v) -> {
            if (!"DataTime".equals(k)) {
                list.add(new LogDetail(k, v));
            }
        });
        dataLog.setLogDetailList(list);
        dataLogService.insertDataLog(dataLog);
    }

    private boolean exclude(String st) {
        List<SysDictData> dictCache = DictUtils.getDictCache(EXCLUDE_ST_KEY);
        if (CollectionUtils.isEmpty(dictCache)) {
            return true;
        }
        return dictCache.stream().map(SysDictData::getDictValue).anyMatch(o -> o.equals(st));
    }

    private boolean checkKey(String st, String cn, String mn) {
        String key = MN_CACHE_PREFIX + st + "_" + cn + "_" + mn;
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey != null && hasKey) {
            return false;
        } else {
            redisTemplate.opsForValue().set(key, "locked", 590, TimeUnit.SECONDS);
            return true;
        }
    }
}
