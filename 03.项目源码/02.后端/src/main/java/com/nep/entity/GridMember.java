package com.nep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * 网格员
 */
@Entity
@Table(name = "grid_member")
public class GridMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gm_id")
    private Integer gmId;

    @Column(name = "gm_name", length = 20, nullable = false)
    private String gmName;

    @Column(name = "gm_code", length = 20, nullable = false, unique = true)
    private String gmCode;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "province_id", nullable = false)
    private Integer provinceId;

    @Column(name = "city_id", nullable = false)
    private Integer cityId;

    @Column(name = "tel", length = 20, nullable = false)
    private String tel;

    @Column(name = "state", nullable = false)
    private Integer state;

    @Column(name = "remarks", length = 200)
    private String remarks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridProvince gridProvince;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridCity gridCity;

    // ========== getters & setters ==========

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public String getGmName() {
        return gmName;
    }

    public void setGmName(String gmName) {
        this.gmName = gmName;
    }

    public String getGmCode() {
        return gmCode;
    }

    public void setGmCode(String gmCode) {
        this.gmCode = gmCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
}
