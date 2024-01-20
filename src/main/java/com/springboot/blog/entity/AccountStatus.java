package com.springboot.blog.entity;

/**
 * @author Thendo
 * @date 2024/01/20
 */
public enum AccountStatus {
    ACTIVE("Active"),
    SUSPENDED("Suspended"),
    AWAITING_CONFIRMATION("Awaiting Confirmation");

    private String description;

    AccountStatus(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
