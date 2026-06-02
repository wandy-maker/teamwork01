package com.nep.entity;

import javax.persistence.*;

/**
 * 系统网格覆盖省区域
 */
@Entity
@Table(name = "grid_province")
public class GridProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name", length = 20, nullable = false)
    private String provinceName;

    @Column(name = "province_abbr", length = 2, nullable = false)
    private String provinceAbbr;

    @Column(name = "remarks", length = 200)
    private String remarks;

    // ========== getters & setters ==========

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceAbbr() {
        return provinceAbbr;
    }

    public void setProvinceAbbr(String provinceAbbr) {
        this.provinceAbbr = provinceAbbr;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
