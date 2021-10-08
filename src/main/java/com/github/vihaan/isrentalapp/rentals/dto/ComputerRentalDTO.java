package com.github.vihaan.isrentalapp.rentals.dto;

import java.time.LocalDate;

public class ComputerRentalDTO {

    private Long id;

    private LocalDate startRentalDate;
    private LocalDate endRentalDate;
    private String rentingPersonEmail;
    private String rentingPersonName;
    private String rentStatus;

    //Computer
    private String computerOtnumber;
    private String computerSerialNumber;
    private String computerProducerName;
    private String computerModel;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getStartRentalDate() {
        return startRentalDate;
    }

    public void setStartRentalDate(LocalDate startRentalDate) {
        this.startRentalDate = startRentalDate;
    }

    public LocalDate getEndRentalDate() {
        return endRentalDate;
    }

    public void setEndRentalDate(LocalDate endRentalDate) {
        this.endRentalDate = endRentalDate;
    }

    public String getRentingPersonEmail() {
        return rentingPersonEmail;
    }

    public void setRentingPersonEmail(String rentingPersonEmail) {
        this.rentingPersonEmail = rentingPersonEmail;
    }

    public String getRentingPersonName() {
        return rentingPersonName;
    }

    public void setRentingPersonName(String rentingPersonName) {
        this.rentingPersonName = rentingPersonName;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }

    public String getComputerOtnumber() {
        return computerOtnumber;
    }

    public void setComputerOtnumber(String computerOtnumber) {
        this.computerOtnumber = computerOtnumber;
    }

    public String getComputerSerialNumber() {
        return computerSerialNumber;
    }

    public void setComputerSerialNumber(String computerSerialNumber) {
        this.computerSerialNumber = computerSerialNumber;
    }

    public String getComputerProducerName() {
        return computerProducerName;
    }

    public void setComputerProducerName(String computerProducerName) {
        this.computerProducerName = computerProducerName;
    }

    public String getComputerModel() {
        return computerModel;
    }

    public void setComputerModel(String computerModel) {
        this.computerModel = computerModel;
    }
}
