package com.nep.entity;

import javax.persistence.*;

/**
 * 系统管理员
 */
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "admin_code", length = 20, nullable = false, unique = true)
    private String adminCode;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "remarks", length = 200)
    private String remarks;

    // ========== getters & setters ==========

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
