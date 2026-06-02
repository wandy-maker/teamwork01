package com.nep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * 空气质量实测统计数据
 */
@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "province_id", nullable = false)
    private Integer provinceId;

    @Column(name = "city_id", nullable = false)
    private Integer cityId;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "so2_value", nullable = false)
    private Integer so2Value;

    @Column(name = "so2_level", nullable = false)
    private Integer so2Level;

    @Column(name = "co_value", nullable = false)
    private Integer coValue;

    @Column(name = "co_level", nullable = false)
    private Integer coLevel;

    @Column(name = "spm_value", nullable = false)
    private Integer spmValue;

    @Column(name = "spm_level", nullable = false)
    private Integer spmLevel;

    @Column(name = "aqi_id", nullable = false)
    private Integer aqiId;

    @Column(name = "confirm_date", length = 20, nullable = false)
    private String confirmDate;

    @Column(name = "confirm_time", length = 20, nullable = false)
    private String confirmTime;

    @Column(name = "gm_id", nullable = false)
    private Integer gmId;

    @Column(name = "fd_id", length = 20, nullable = false)
    private String fdId;

    @Column(name = "information", length = 400, nullable = false)
    private String information;

    @Column(name = "remarks", length = 200)
    private String remarks;

    // ===== 关联关系 =====

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridProvince gridProvince;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridCity gridCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aqi_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Aqi aqi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gm_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridMember gridMember;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fd_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Supervisor supervisor;

    // ========== getters & setters ==========

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSo2Value() {
        return so2Value;
    }

    public void setSo2Value(Integer so2Value) {
        this.so2Value = so2Value;
    }

    public Integer getSo2Level() {
        return so2Level;
    }

    public void setSo2Level(Integer so2Level) {
        this.so2Level = so2Level;
    }

    public Integer getCoValue() {
        return coValue;
    }

    public void setCoValue(Integer coValue) {
        this.coValue = coValue;
    }

    public Integer getCoLevel() {
        return coLevel;
    }

    public void setCoLevel(Integer coLevel) {
        this.coLevel = coLevel;
    }

    public Integer getSpmValue() {
        return spmValue;
    }

    public void setSpmValue(Integer spmValue) {
        this.spmValue = spmValue;
    }

    public Integer getSpmLevel() {
        return spmLevel;
    }

    public void setSpmLevel(Integer spmLevel) {
        this.spmLevel = spmLevel;
    }

    public Integer getAqiId() {
        return aqiId;
    }

    public void setAqiId(Integer aqiId) {
        this.aqiId = aqiId;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public GridProvince getGridProvince() {
        return gridProvince;
    }

    public void setGridProvince(GridProvince gridProvince) {
        this.gridProvince = gridProvince;
    }

    public GridCity getGridCity() {
        return gridCity;
    }

    public void setGridCity(GridCity gridCity) {
        this.gridCity = gridCity;
    }

    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public GridMember getGridMember() {
        return gridMember;
    }

    public void setGridMember(GridMember gridMember) {
        this.gridMember = gridMember;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
}
