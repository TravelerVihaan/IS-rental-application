package onet.grupa.isrentalapplication.domain.computers;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "operating_systems")
public class OperatingSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_os")
    private Long id;

    @NotEmpty
    @Column(name = "operating_system", nullable = false, unique = true)
    private String operatingSystem;
}
