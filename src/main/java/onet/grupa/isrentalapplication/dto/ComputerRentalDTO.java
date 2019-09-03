package onet.grupa.isrentalapplication.dto;

import onet.grupa.isrentalapplication.domain.rentals.RentStatus;

import java.time.LocalDate;

public class ComputerRentalDTO {

    private LocalDate startRentalDate;
    private LocalDate endRentalDate;
    private String rentingPersonemail;
    private String rentingPersonName;
    private RentStatus rentStatus;

    //Computer
    private String OTNumber;
    private String serialNumber;
    private String producerName;
    private String computerModel;

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

    public String getRentingPersonemail() {
        return rentingPersonemail;
    }

    public void setRentingPersonemail(String rentingPersonemail) {
        this.rentingPersonemail = rentingPersonemail;
    }

    public String getRentingPersonName() {
        return rentingPersonName;
    }

    public void setRentingPersonName(String rentingPersonName) {
        this.rentingPersonName = rentingPersonName;
    }

    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
    }

    public String getOTNumber() {
        return OTNumber;
    }

    public void setOTNumber(String OTNumber) {
        this.OTNumber = OTNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getComputerModel() {
        return computerModel;
    }

    public void setComputerModel(String computerModel) {
        this.computerModel = computerModel;
    }
}
