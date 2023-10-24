package com.aytronn.kibvet.enums;

public enum Permission {
    POST("post"),
    DELETE("delete"),
    PUT("put"),
    GET("get");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}