package com.github.vihaan.isrentalapp.rentals.entities;

import com.github.vihaan.isrentalapp.devices.entities.ComputerEntity;
import com.github.vihaan.isrentalapp.users.entities.User;
import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "computer_rentals")
public class ComputerRental extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental")
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startRentalDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endRentalDate;

    @Column(name = "email", nullable = false)
    private String rentingPersonEmail;

    @Column(name = "who_rent", nullable = false)
    private String rentingPersonName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "computer_id")
    private ComputerEntity rentedComputerEntity;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "rent_status_id")
    private RentStatus rentStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "who_set_status")
    private User whoSetStatus;

    public ComputerRental(){}
    public ComputerRental(LocalDate startRentalDate, LocalDate endRentalDate, String rentingPersonEmail, String rentingPersonName, ComputerEntity rentedComputerEntity, RentStatus rentStatus) {
        this.startRentalDate = startRentalDate;
        this.endRentalDate = endRentalDate;
        this.rentingPersonEmail = rentingPersonEmail;
        this.rentingPersonName = rentingPersonName;
        this.rentedComputerEntity = rentedComputerEntity;
        this.rentStatus = rentStatus;
    }

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

    public ComputerEntity getRentedComputer() {
        return rentedComputerEntity;
    }

    public void setRentedComputer(ComputerEntity rentedComputerEntity) {
        this.rentedComputerEntity = rentedComputerEntity;
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
        ComputerRental computerRental = (ComputerRental) o;
        return getUuid().equals(computerRental.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "ComputerRentals{" +
                "id=" + id +
                ", startRentalDate=" + startRentalDate +
                ", endRentalDate=" + endRentalDate +
                ", rentingPersonemail='" + rentingPersonEmail + '\'' +
                ", rentingPersonName='" + rentingPersonName + '\'' +
                ", rentedComputer=" + rentedComputerEntity +
                ", rentStatus=" + rentStatus +
                ", whoSetStatus=" + whoSetStatus +
                '}';
    }
}
