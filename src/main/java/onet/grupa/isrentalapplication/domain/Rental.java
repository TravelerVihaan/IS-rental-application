package onet.grupa.isrentalapplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental")
    private Long id;

    public Rental() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
