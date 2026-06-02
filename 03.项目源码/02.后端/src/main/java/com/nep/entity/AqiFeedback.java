package com.nep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * 空气质量公众监督反馈信息
 */
@Entity
@Table(name = "aqi_feedback")
public class AqiFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "af_id")
    private Integer afId;

    @Column(name = "tel_id", length = 20, nullable = false)
    private String telId;

    @Column(name = "province_id", nullable = false)
    private Integer provinceId;

    @Column(name = "city_id", nullable = false)
    private Integer cityId;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "information", length = 400, nullable = false)
    private String information;

    @Column(name = "estimated_grade", nullable = false)
    private Integer estimatedGrade;

    @Column(name = "af_date", length = 20, nullable = false)
    private String afDate;

    @Column(name = "af_time", length = 20, nullable = false)
    private String afTime;

    @Column(name = "gm_id", nullable = false)
    private Integer gmId;

    @Column(name = "assign_date", length = 20)
    private String assignDate;

    @Column(name = "assign_time", length = 20)
    private String assignTime;

    @Column(name = "state", nullable = false)
    private Integer state;

    @Column(name = "remarks", length = 200)
    private String remarks;

    // ===== 关联关系 =====

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tel_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Supervisor supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridProvince gridProvince;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridCity gridCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estimated_grade", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Aqi aqi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gm_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridMember gridMember;

    // ========== getters & setters ==========

    public Integer getAfId() {
        return afId;
    }

    public void setAfId(Integer afId) {
        this.afId = afId;
    }

    public String getTelId() {
        return telId;
    }

    public void setTelId(String telId) {
        this.telId = telId;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getEstimatedGrade() {
        return estimatedGrade;
    }

    public void setEstimatedGrade(Integer estimatedGrade) {
        this.estimatedGrade = estimatedGrade;
    }

    public String getAfDate() {
        return afDate;
    }

    public void setAfDate(String afDate) {
        this.afDate = afDate;
    }

    public String getAfTime() {
        return afTime;
    }

    public void setAfTime(String afTime) {
        this.afTime = afTime;
    }

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
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
}
