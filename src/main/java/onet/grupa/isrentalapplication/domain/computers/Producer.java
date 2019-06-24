package onet.grupa.isrentalapplication.domain.computers;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "producers_computer")
public class Producer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producer")
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false, unique = true)
    private String producerName;

    @OneToMany(mappedBy = "producer")
    private List<Computer> computers;

    public Producer(){}
    public Producer(@NotEmpty String producerName) {
        this.producerName = producerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", producerName='" + producerName + '\'' +
                '}';
    }
}
