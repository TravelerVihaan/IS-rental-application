package onet.grupa.isrentalapplication.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rentals")
public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental")
    private Long id;

    public Rental() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
