package com.nep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * 系统网格覆盖市区域
 */
@Entity
@Table(name = "grid_city")
public class GridCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name", length = 20, nullable = false)
    private String cityName;

    @Column(name = "province_id", nullable = false)
    private Integer provinceId;

    @Column(name = "remarks", length = 200)
    private String remarks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private GridProvince gridProvince;

    // ========== getters & setters ==========

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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
}
