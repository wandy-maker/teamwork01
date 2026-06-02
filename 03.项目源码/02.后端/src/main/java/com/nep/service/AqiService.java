package com.nep.service;

import com.nep.entity.Aqi;
import com.nep.repository.AqiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 空气质量指数业务逻辑
 */
@Service
public class AqiService {

    @Autowired
    private AqiRepository aqiRepository;

    /**
     * 查询所有AQI等级数据（按ID升序）
     * 前端用于动态计算实测等级
     *
     * @return AQI等级列表
     */
    public List<Aqi> listAqiAll() {
        return aqiRepository.findAllByOrderByAqiIdAsc();
    }
}
