package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "models_computer")
class ComputerModel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model")
    private Long id;

    @Column(name = "model_name", nullable = false, unique = true)
    private String modelName;

    //@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "producer_id")
    private ComputerProducer computerProducer;

    public ComputerModel(){}
    public ComputerModel(String modelName) {
        this.modelName = modelName;
    }
    public ComputerModel(String modelName, ComputerProducer computerProducer) {
        this.modelName = modelName;
        this.computerProducer = computerProducer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public ComputerProducer getComputerProducer() {
        return computerProducer;
    }

    public void setComputerProducer(ComputerProducer computerProducer) {
        this.computerProducer = computerProducer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerModel computerModel = (ComputerModel) o;
        return getUuid().equals(computerModel.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "ComputerModel{" +
                "id=" + id +
                ", model='" + modelName + '\'' +
                ", producer=" + computerProducer +
                '}';
    }
}
