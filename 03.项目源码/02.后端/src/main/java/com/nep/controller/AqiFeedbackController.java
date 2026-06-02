package com.nep.controller;

import com.nep.entity.AqiFeedback;
import com.nep.service.AqiFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 空气质量反馈信息接口
 *
 * 前端调用的三个端点:
 * 1. POST /aqiFeedback/listAqiFeedbackByGmId    — 查询网格员已指派反馈列表
 * 2. POST /aqiFeedback/getAqiFeedbackById       — 查询反馈详情
 * 3. POST /aqiFeedback/updateAqiFeedbackState   — 更新反馈状态
 */
@RestController
@RequestMapping("/aqiFeedback")
public class AqiFeedbackController {

    @Autowired
    private AqiFeedbackService aqiFeedbackService;

    /**
     * 查询已指派给某网格员的反馈列表
     * Body: { "gmId": 1, "state": 1 }
     */
    @PostMapping("/listAqiFeedbackByGmId")
    public List<AqiFeedback> listAqiFeedbackByGmId(@RequestBody Map<String, Object> params) {
        Integer gmId = (Integer) params.get("gmId");
        Integer state = (Integer) params.get("state");
        return aqiFeedbackService.listAqiFeedbackByGmId(gmId, state);
    }

    /**
     * 根据反馈ID查询反馈详情（含监督员、省市区、AQI等级、网格员信息）
     * Body: { "afId": 18 }
     */
    @PostMapping("/getAqiFeedbackById")
    public AqiFeedback getAqiFeedbackById(@RequestBody Map<String, Object> params) {
        Integer afId = (Integer) params.get("afId");
        return aqiFeedbackService.getAqiFeedbackById(afId);
    }

    /**
     * 更新反馈信息状态（如：已指派→已确认）
     * Body: { "afId": 18, "state": 2 }
     * 返回: 影响行数 (int)
     */
    @PostMapping("/updateAqiFeedbackState")
    public int updateAqiFeedbackState(@RequestBody Map<String, Object> params) {
        Integer afId = (Integer) params.get("afId");
        Integer state = (Integer) params.get("state");
        return aqiFeedbackService.updateAqiFeedbackState(afId, state);
    }
}
