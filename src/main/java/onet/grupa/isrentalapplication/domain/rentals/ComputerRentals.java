package onet.grupa.isrentalapplication.domain.rentals;

import onet.grupa.isrentalapplication.domain.computers.Computer;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "computer_rentals")
public class ComputerRentals implements Serializable {

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
    private String rentingPersonemail;

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

    public ComputerRentals(){}

    public ComputerRentals(@NotNull LocalDate startRentalDate, @NotNull LocalDate endRentalDate, @Email @NotEmpty String rentingPersonemail, @NotEmpty String rentingPersonName, @NotNull Computer rentedComputer, @NotNull RentStatus rentStatus) {
        this.startRentalDate = startRentalDate;
        this.endRentalDate = endRentalDate;
        this.rentingPersonemail = rentingPersonemail;
        this.rentingPersonName = rentingPersonName;
        this.rentedComputer = rentedComputer;
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

    @Override
    public String toString() {
        return "ComputerRentals{" +
                "id=" + id +
                ", startRentalDate=" + startRentalDate +
                ", endRentalDate=" + endRentalDate +
                ", rentingPersonemail='" + rentingPersonemail + '\'' +
                ", rentingPersonName='" + rentingPersonName + '\'' +
                ", rentedComputer=" + rentedComputer +
                ", rentStatus=" + rentStatus +
                '}';
    }
}
