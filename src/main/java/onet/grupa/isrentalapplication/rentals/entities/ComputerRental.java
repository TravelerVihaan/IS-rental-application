package onet.grupa.isrentalapplication.rentals.entities;

import onet.grupa.isrentalapplication.domain.users.User;
import onet.grupa.isrentalapplication.devices.entities.Computer;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "computer_rentals")
public class ComputerRental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental")
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startRentalDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endRentalDate;

    @Email
    @NotEmpty
    @Column(name = "email", nullable = false)
    private String rentingPersonEmail;

    @NotEmpty
    @Column(name = "who_rent", nullable = false)
    private String rentingPersonName;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "computer_id")
    private Computer rentedComputer;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "rent_status_id")
    private RentStatus rentStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "who_set_status")
    private User whoSetStatus;

    public ComputerRental(){}

    public ComputerRental(@NotNull LocalDate startRentalDate, @NotNull LocalDate endRentalDate, @Email @NotEmpty String rentingPersonEmail, @NotEmpty String rentingPersonName, @NotNull Computer rentedComputer, @NotNull RentStatus rentStatus) {
        this.startRentalDate = startRentalDate;
        this.endRentalDate = endRentalDate;
        this.rentingPersonEmail = rentingPersonEmail;
        this.rentingPersonName = rentingPersonName;
        this.rentedComputer = rentedComputer;
        this.rentStatus = rentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerRental that = (ComputerRental) o;
        return id.equals(that.id) &&
                startRentalDate.equals(that.startRentalDate) &&
                endRentalDate.equals(that.endRentalDate) &&
                rentingPersonEmail.equals(that.rentingPersonEmail) &&
                rentingPersonName.equals(that.rentingPersonName) &&
                Objects.equals(rentedComputer, that.rentedComputer) &&
                Objects.equals(rentStatus, that.rentStatus) &&
                Objects.equals(whoSetStatus, that.whoSetStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startRentalDate, endRentalDate, rentingPersonEmail, rentingPersonName, rentedComputer, rentStatus, whoSetStatus);
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
    public String toString() {
        return "ComputerRentals{" +
                "id=" + id +
                ", startRentalDate=" + startRentalDate +
                ", endRentalDate=" + endRentalDate +
                ", rentingPersonemail='" + rentingPersonEmail + '\'' +
                ", rentingPersonName='" + rentingPersonName + '\'' +
                ", rentedComputer=" + rentedComputer +
                ", rentStatus=" + rentStatus +
                ", whoSetStatus=" + whoSetStatus +
                '}';
    }
}
