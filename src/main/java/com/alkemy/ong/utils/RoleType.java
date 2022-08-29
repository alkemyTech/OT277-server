package com.alkemy.ong.utils;

public enum RoleType {
    ADMIN, USER;

    private static final String ROLE_PREFIX = "ROLE_";

    public String getFullRoleName() {
        return ROLE_PREFIX + this.name();
    }
}
