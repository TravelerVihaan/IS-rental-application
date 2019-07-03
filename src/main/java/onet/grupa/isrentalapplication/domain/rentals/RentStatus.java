package onet.grupa.isrentalapplication.domain.rentals;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rent_status")
public class RentStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String status;

    @OneToMany(mappedBy = "rentStatus")
    private List<ComputerRental> computerRentals;

    public RentStatus(){}
    public RentStatus(@NotEmpty String status) {
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public List<ComputerRental> getComputerRentals() {
        return computerRentals;
    }

    public void setComputerRentals(List<ComputerRental> computerRentals) {
        this.computerRentals = computerRentals;
    }

    @Override
    public String toString() {
        return "RentStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
