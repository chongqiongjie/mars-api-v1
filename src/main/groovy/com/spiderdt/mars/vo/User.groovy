package com.spiderdt.mars.vo

/**
 * @Title:
 * @Package com.spiderdt.mars.entity
 * @Description:
 * @author ranran
 * @date 2017/3/16 9:33
 * @version V1.0
 */

public class User {
    private String username
    private String password

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    User(getUsername, getPassword) {
        username = getUsername
        password = getPassword
    }

    void setPassword(String password) {
        this.password = password
    }

    void setUsername(String username) {
        this.username = username
    }
}

