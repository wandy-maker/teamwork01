package com.nep.entity;

import javax.persistence.*;

/**
 * 空气质量指数级别
 */
@Entity
@Table(name = "aqi")
public class Aqi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aqi_id")
    private Integer aqiId;

    @Column(name = "chinese_explain", length = 10, nullable = false)
    private String chineseExplain;

    @Column(name = "aqi_explain", length = 20, nullable = false)
    private String aqiExplain;

    @Column(name = "color", length = 7, nullable = false)
    private String color;

    @Column(name = "health_impact", length = 500, nullable = false)
    private String healthImpact;

    @Column(name = "take_steps", length = 500, nullable = false)
    private String takeSteps;

    @Column(name = "so2_min", nullable = false)
    private Integer so2Min;

    @Column(name = "so2_max", nullable = false)
    private Integer so2Max;

    @Column(name = "co_min", nullable = false)
    private Integer coMin;

    @Column(name = "co_max", nullable = false)
    private Integer coMax;

    @Column(name = "spm_min", nullable = false)
    private Integer spmMin;

    @Column(name = "spm_max", nullable = false)
    private Integer spmMax;

    @Column(name = "remarks", length = 100)
    private String remarks;

    // ========== getters & setters ==========

    public Integer getAqiId() {
        return aqiId;
    }

    public void setAqiId(Integer aqiId) {
        this.aqiId = aqiId;
    }

    public String getChineseExplain() {
        return chineseExplain;
    }

    public void setChineseExplain(String chineseExplain) {
        this.chineseExplain = chineseExplain;
    }

    public String getAqiExplain() {
        return aqiExplain;
    }

    public void setAqiExplain(String aqiExplain) {
        this.aqiExplain = aqiExplain;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHealthImpact() {
        return healthImpact;
    }

    public void setHealthImpact(String healthImpact) {
        this.healthImpact = healthImpact;
    }

    public String getTakeSteps() {
        return takeSteps;
    }

    public void setTakeSteps(String takeSteps) {
        this.takeSteps = takeSteps;
    }

    public Integer getSo2Min() {
        return so2Min;
    }

    public void setSo2Min(Integer so2Min) {
        this.so2Min = so2Min;
    }

    public Integer getSo2Max() {
        return so2Max;
    }

    public void setSo2Max(Integer so2Max) {
        this.so2Max = so2Max;
    }

    public Integer getCoMin() {
        return coMin;
    }

    public void setCoMin(Integer coMin) {
        this.coMin = coMin;
    }

    public Integer getCoMax() {
        return coMax;
    }

    public void setCoMax(Integer coMax) {
        this.coMax = coMax;
    }

    public Integer getSpmMin() {
        return spmMin;
    }

    public void setSpmMin(Integer spmMin) {
        this.spmMin = spmMin;
    }

    public Integer getSpmMax() {
        return spmMax;
    }

    public void setSpmMax(Integer spmMax) {
        this.spmMax = spmMax;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
