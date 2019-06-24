package onet.grupa.isrentalapplication.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

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

    public RentStatus(@NotEmpty String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RentStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
