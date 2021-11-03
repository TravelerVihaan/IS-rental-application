package com.github.vihaan.isrentalapp.rentals;

import java.util.Arrays;

public enum RentStatus {
    COMPLETED("Completed"),
    WAITING("Waiting"),
    ACTIVE("Active"),
    OVERDUE("Overdue"),
    REJECTED("Rejected"),
    UNDEFINED("Undefined");

    private final String rentStatus;

    RentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }

    String getRentStatus() {
        return rentStatus;
    }

    static RentStatus createFromString(String rentStatusName) {
        return Arrays.stream(RentStatus.values())
                .filter(rentStatus -> rentStatus.rentStatus.equalsIgnoreCase((rentStatusName)))
                .findFirst()
                .orElse(RentStatus.UNDEFINED);
    }
}
