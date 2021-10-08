package onet.grupa.isrentalapplication.devices.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "producers_computer")
public class ComputerProducer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producer")
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false, unique = true)
    private String producerName;

    @OneToMany(mappedBy = "computerProducer")//(mappedBy = "computerProducer", cascade = {CascadeType.ALL})
    private List<ComputerModel> computerModels;

    public ComputerProducer(){}
    public ComputerProducer(@NotEmpty String producerName) {
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

    public List<ComputerModel> getComputerModels() {
        return computerModels;
    }

    public void setComputerModels(List<ComputerModel> computerModels) {
        this.computerModels = computerModels;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", producerName='" + producerName + '\'' +
                '}';
    }
}
