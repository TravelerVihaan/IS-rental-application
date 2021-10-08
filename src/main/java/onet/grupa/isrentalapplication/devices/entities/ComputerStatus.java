package onet.grupa.isrentalapplication.devices.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "computer_status")
public class ComputerStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer_status")
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String status;

    @OneToMany(mappedBy = "computerStatus")
    private List<Computer> computers;

    public ComputerStatus(){}
    public ComputerStatus(String status) {
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

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return "ComputerStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
