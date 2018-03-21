package com.clom.my.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class MyUser implements Serializable {
    private Long id;
    @NotNull(message = "姓名不为能空")
    private String name;
    @NotNull(message = "密码不能为空")
    private String pwd;
    @NotNull(message = "手机号码不能为空")
    private String phone;
    @Email(message = "请填写email")
    private String email;

    private String address;

    private String qq;

    private String wx;

    private String wb;

    private String pwdClear;

    private Short isperfect;

    private String createby;

    private Date createdate;

    private String updateby;

    private Date updatedate;

    private Short activeflag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx == null ? null : wx.trim();
    }

    public String getWb() {
        return wb;
    }

    public void setWb(String wb) {
        this.wb = wb == null ? null : wb.trim();
    }

    public String getPwdClear() {
        return pwdClear;
    }

    public void setPwdClear(String pwdClear) {
        this.pwdClear = pwdClear == null ? null : pwdClear.trim();
    }

    public Short getIsperfect() {
        return isperfect;
    }

    public void setIsperfect(Short isperfect) {
        this.isperfect = isperfect;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Short getActiveflag() {
        return activeflag;
    }

    public void setActiveflag(Short activeflag) {
        this.activeflag = activeflag;
    }
}