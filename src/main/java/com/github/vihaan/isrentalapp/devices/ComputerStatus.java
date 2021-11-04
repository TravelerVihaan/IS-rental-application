package com.github.vihaan.isrentalapp.devices;

import java.util.Arrays;

public enum ComputerStatus {
    AVAILABLE("Available"),
    RENTED("Rented"),
    BROKEN("Broken"),
    RESERVED("Reserved");

    private final String computerStatus;

    ComputerStatus(String computerStatus) {
        this.computerStatus = computerStatus;
    }

    public String getComputerStatus() {
        return computerStatus;
    }

    public static ComputerStatus createFromString(String computerStatusName) {
        return Arrays.stream(ComputerStatus.values())
                .filter(computerStatus -> computerStatus.computerStatus.equalsIgnoreCase((computerStatusName)))
                .findFirst()
                .orElse(ComputerStatus.AVAILABLE);
    }
}
