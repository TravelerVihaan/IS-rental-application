package onet.grupa.isrentalapplication.domain.computers;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "models_computer")
public class ComputerModel implements Serializable {

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
    private ComputerProducer computerProducer;

    public ComputerModel(){}
    public ComputerModel(@NotEmpty String model) {
        this.model = model;
    }
    public ComputerModel(@NotEmpty String model, @NotNull ComputerProducer computerProducer) {
        this.model = model;
        this.computerProducer = computerProducer;
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

    public ComputerProducer getComputerProducer() {
        return computerProducer;
    }

    public void setComputerProducer(ComputerProducer computerProducer) {
        this.computerProducer = computerProducer;
    }

    @Override
    public String toString() {
        return "ComputerModel{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", producer=" + computerProducer +
                '}';
    }
}
