package com.github.vihaan.isrentalapp.rentals;

public enum RentStatus {
    COMPLETED("completed"),
    WAITING("waiting"),
    ACTIVE("active"),
    OVERDUE("overdue"),
    REJECTED("rejected"),
    CANCELED("canceled");

    private String status;

    RentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
