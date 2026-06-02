package com.nep.controller;

import com.nep.entity.Aqi;
import com.nep.service.AqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 空气质量指数接口
 *
 * 前端调用: POST /aqi/listAqiAll
 * 返回: AQI等级列表（用于前端动态计算实测等级颜色和描述）
 */
@RestController
@RequestMapping("/aqi")
public class AqiController {

    @Autowired
    private AqiService aqiService;

    /**
     * 查询全部AQI等级数据
     */
    @PostMapping("/listAqiAll")
    public List<Aqi> listAqiAll() {
        return aqiService.listAqiAll();
    }
}
