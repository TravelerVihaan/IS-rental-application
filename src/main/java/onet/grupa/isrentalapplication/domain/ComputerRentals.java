package onet.grupa.isrentalapplication.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "computer_rentals")
public class ComputerRentals implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental")
    private Long id;

    public ComputerRentals() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
