package com.github.vihaan.isrentalapp.rentals;

import java.util.Arrays;

public enum RentStatus {
    NEW(IRentalConstants.RENT_STATUS_NEW),
    COMPLETED(IRentalConstants.RENT_STATUS_COMPLETED),
    WAITING(IRentalConstants.RENT_STATUS_WAITING),
    ACTIVE(IRentalConstants.RENT_STATUS_ACTIVE),
    OVERDUE(IRentalConstants.RENT_STATUS_OVERDUE),
    REJECTED(IRentalConstants.RENT_STATUS_REJECTED),
    CANCELED(IRentalConstants.RENT_STATUS_CANCELED);

    private String status;

    RentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static RentStatus fromString(String status){
        return Arrays.stream(RentStatus.values())
                .filter(rentStatus -> rentStatus.getStatus().equalsIgnoreCase(status))
                .findFirst().orElse(RentStatus.NEW);
    }
}
