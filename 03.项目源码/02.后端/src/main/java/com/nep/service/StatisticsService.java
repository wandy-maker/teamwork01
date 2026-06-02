package com.nep.service;

import com.nep.entity.Statistics;
import com.nep.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实测统计数据业务逻辑
 */
@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    /**
     * 保存网格员提交的实测数据
     * 前端传入完整参数对象
     *
     * @param statistics 统计数据实体
     * @return 保存后的实体（含生成ID）
     */
    public Statistics saveStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }
}
