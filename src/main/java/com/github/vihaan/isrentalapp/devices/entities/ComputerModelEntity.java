package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "computer_models")
class ComputerModelEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model")
    private Long id;

    @Column(name = "model_name", nullable = false, unique = true)
    private String modelName;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "producer_id")
    private ComputerProducerEntity computerProducerEntity;

    public ComputerModelEntity(){}
    public ComputerModelEntity(String modelName, ComputerProducerEntity computerProducerEntity) {
        this.modelName = modelName;
        this.computerProducerEntity = computerProducerEntity;
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

    public ComputerProducerEntity getComputerProducer() {
        return computerProducerEntity;
    }

    public void setComputerProducer(ComputerProducerEntity computerProducerEntity) {
        this.computerProducerEntity = computerProducerEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerModelEntity computerModelEntity = (ComputerModelEntity) o;
        return getUuid().equals(computerModelEntity.getUuid());
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
                ", producer=" + computerProducerEntity +
                '}';
    }
}
