package com.github.vihaan.isrentalapp.rentals;

import com.github.vihaan.isrentalapp.devices.Computer;
import com.github.vihaan.isrentalapp.users.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class ComputerRental {

    @NotNull
    private LocalDate startRentalDate;
    @NotNull
    private LocalDate endRentalDate;
    @NotEmpty
    private String rentingPersonEmail;
    @NotEmpty
    private String rentingPersonName;
    @NotNull
    private Computer rentedComputer;
    @NotNull
    private RentStatus rentStatus;
    @NotNull
    private User whoSetStatus;

    public ComputerRental() {}
    public ComputerRental(LocalDate startRentalDate,
                          LocalDate endRentalDate,
                          String rentingPersonEmail,
                          String rentingPersonName,
                          Computer rentedComputer,
                          RentStatus rentStatus,
                          User whoSetStatus) {
        this.startRentalDate = startRentalDate;
        this.endRentalDate = endRentalDate;
        this.rentingPersonEmail = rentingPersonEmail;
        this.rentingPersonName = rentingPersonName;
        this.rentedComputer = rentedComputer;
        this.rentStatus = rentStatus;
        this.whoSetStatus = whoSetStatus;
    }

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

    public Computer getRentedComputer() {
        return rentedComputer;
    }

    public void setRentedComputer(Computer rentedComputer) {
        this.rentedComputer = rentedComputer;
    }

    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
    }

    public User getWhoSetStatus() {
        return whoSetStatus;
    }

    public void setWhoSetStatus(User whoSetStatus) {
        this.whoSetStatus = whoSetStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerRental that = (ComputerRental) o;
        return Objects.equals(startRentalDate, that.startRentalDate) && Objects.equals(endRentalDate, that.endRentalDate) && Objects.equals(rentingPersonEmail, that.rentingPersonEmail) && Objects.equals(rentingPersonName, that.rentingPersonName) && rentStatus == that.rentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startRentalDate, endRentalDate, rentingPersonEmail, rentingPersonName, rentStatus);
    }

    @Override
    public String toString() {
        return "ComputerRental{" +
                "startRentalDate=" + startRentalDate +
                ", endRentalDate=" + endRentalDate +
                ", rentingPersonEmail='" + rentingPersonEmail + '\'' +
                ", rentingPersonName='" + rentingPersonName + '\'' +
                ", rentedComputer=" + rentedComputer +
                ", rentStatus=" + rentStatus +
                ", whoSetStatus=" + whoSetStatus +
                '}';
    }
}
