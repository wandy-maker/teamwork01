package com.nep.service;

import com.nep.entity.AqiFeedback;
import com.nep.repository.AqiFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 空气质量反馈信息业务逻辑
 */
@Service
public class AqiFeedbackService {

    @Autowired
    private AqiFeedbackRepository aqiFeedbackRepository;

    /**
     * 根据网格员ID查询已指派的反馈列表
     * 前端传入 {gmId, state: 1}
     *
     * @param gmId  网格员ID
     * @param state 状态 (1=已指派)
     * @return 反馈列表（含关联的监督员、省市区、AQI等级信息）
     */
    public List<AqiFeedback> listAqiFeedbackByGmId(Integer gmId, Integer state) {
        return aqiFeedbackRepository.findByGmIdAndStateOrderByAfDateDescAfTimeDesc(gmId, state);
    }

    /**
     * 根据主键查询反馈详细信息
     * 前端传入 {afId}
     *
     * @param afId 反馈ID
     * @return 反馈详情（含全部关联数据）
     */
    public AqiFeedback getAqiFeedbackById(Integer afId) {
        return aqiFeedbackRepository.findById(afId).orElse(null);
    }

    /**
     * 更新反馈信息状态
     * 前端传入 {afId, state}
     *
     * @param afId  反馈ID
     * @param state 新状态 (2=已确认)
     * @return 影响行数
     */
    public int updateAqiFeedbackState(Integer afId, Integer state) {
        return aqiFeedbackRepository.updateStateById(afId, state);
    }
}
