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
    private String computerProducer;
    private String computerModel;

}
