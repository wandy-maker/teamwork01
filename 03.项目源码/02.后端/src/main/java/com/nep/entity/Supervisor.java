package com.nep.entity;

import javax.persistence.*;

/**
 * 公众监督员
 */
@Entity
@Table(name = "supervisor")
public class Supervisor {

    @Id
    @Column(name = "tel_id", length = 11)
    private String telId;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "real_name", length = 20, nullable = false)
    private String realName;

    @Column(name = "birthday", length = 20, nullable = false)
    private String birthday;

    @Column(name = "sex", nullable = false)
    private Integer sex;

    @Column(name = "remarks", length = 100)
    private String remarks;

    // ========== getters & setters ==========

    public String getTelId() {
        return telId;
    }

    public void setTelId(String telId) {
        this.telId = telId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
