/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.bean;

/**
 *
 * @author Staff
 */
public class RegisterInfoBean {

    private String username = "";
    private String name = "";
    private String email = "";
    private String phone = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
