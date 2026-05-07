package com.example.esport_clash.auth.domain.model;

import com.example.esport_clash.core.domain.model.BaseEntity;

public class User extends BaseEntity {
    private String email;
    private String password;

    public User() {}

    public User(String id, String email, String password) {
        super(id);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
