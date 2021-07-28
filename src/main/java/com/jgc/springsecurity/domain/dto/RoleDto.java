package com.jgc.springsecurity.domain.dto;

import com.jgc.springsecurity.domain.Auth;
import com.jgc.springsecurity.domain.Role;

import java.util.List;

public class RoleDto extends Role {
    private List<Auth> list;

    public List<Auth> getList() {
        return list;
    }

    public void setList(List<Auth> list) {
        this.list = list;
    }
}
