package com.nep.controller;

import com.nep.entity.Statistics;
import com.nep.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 实测统计数据接口
 *
 * 前端调用: POST /statistics/saveStatistics
 * Body: { provinceId, cityId, address, so2Value, so2Level, coValue, coLevel,
 *         spmValue, spmLevel, aqiId, confirmDate, confirmTime, gmId,
 *         fdId, information }
 * 返回: 1 表示成功
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 保存网格员提交的实测统计数据
     */
    @PostMapping("/saveStatistics")
    public int saveStatistics(@RequestBody Map<String, Object> params) {
        Statistics statistics = new Statistics();
        statistics.setProvinceId((Integer) params.get("provinceId"));
        statistics.setCityId((Integer) params.get("cityId"));
        statistics.setAddress((String) params.get("address"));
        statistics.setSo2Value(Integer.valueOf(params.get("so2Value").toString()));
        statistics.setSo2Level((Integer) params.get("so2Level"));
        statistics.setCoValue(Integer.valueOf(params.get("coValue").toString()));
        statistics.setCoLevel((Integer) params.get("coLevel"));
        statistics.setSpmValue(Integer.valueOf(params.get("spmValue").toString()));
        statistics.setSpmLevel((Integer) params.get("spmLevel"));
        statistics.setAqiId((Integer) params.get("aqiId"));
        statistics.setConfirmDate((String) params.get("confirmDate"));
        statistics.setConfirmTime((String) params.get("confirmTime"));
        statistics.setGmId((Integer) params.get("gmId"));
        statistics.setFdId((String) params.get("fdId"));
        statistics.setInformation((String) params.get("information"));

        statisticsService.saveStatistics(statistics);
        return 1;
    }
}
