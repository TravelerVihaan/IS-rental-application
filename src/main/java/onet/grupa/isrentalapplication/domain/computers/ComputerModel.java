package onet.grupa.isrentalapplication.domain.computers;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "computer_models")
public class ComputerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model")
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String model;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "producer_id")
    private Producer producer;

    public ComputerModel(){}
    public ComputerModel(@NotEmpty String model, @NotNull Producer producer) {
        this.model = model;
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "ComputerModel{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", producer=" + producer +
                '}';
    }
}
