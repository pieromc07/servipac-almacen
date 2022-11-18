package com.servipac.almacen.security.common;

public enum Role {
    ADMIN, ALMACEN;

    private static final String ROLE_PREFIX = "ROLE_";

    public String getAuthority() {
        return ROLE_PREFIX + this.name();
    }
}
