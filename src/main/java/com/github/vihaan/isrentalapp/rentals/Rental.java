package com.github.vihaan.isrentalapp.rentals;

import com.github.vihaan.isrentalapp.devices.Computer;
import com.github.vihaan.isrentalapp.devices.entities.ComputerEntity;
import com.github.vihaan.isrentalapp.users.User;

import java.time.LocalDate;
import java.util.Objects;

public class Rental {

    private LocalDate startRentalDate;
    private LocalDate endRentalDate;
    private String rentingPersonEmail;
    private String rentingPersonName;
    private Computer rentedComputer;
    private RentStatus rentStatus;
    private User whoSetStatus;

    public Rental() {}
    public Rental(LocalDate startRentalDate, LocalDate endRentalDate,
                  String rentingPersonEmail, String rentingPersonName,
                  Computer rentedComputer, RentStatus rentStatus, User whoSetStatus) {
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
        Rental rental = (Rental) o;
        return Objects.equals(getStartRentalDate(), rental.getStartRentalDate()) && Objects.equals(getEndRentalDate(), rental.getEndRentalDate()) && Objects.equals(getRentingPersonEmail(), rental.getRentingPersonEmail()) && Objects.equals(getRentingPersonName(), rental.getRentingPersonName()) && Objects.equals(getRentedComputer(), rental.getRentedComputer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartRentalDate(), getEndRentalDate(), getRentingPersonEmail(), getRentingPersonName(), getRentedComputer());
    }

    @Override
    public String toString() {
        return "Rental{" +
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
