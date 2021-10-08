package com.github.vihaan.isrentalapp.util;

import java.util.UUID;

public abstract class BaseEntity {
    private String uuid = UUID.randomUUID().toString();

    protected String getUuid() {
        return uuid;
    }
}
