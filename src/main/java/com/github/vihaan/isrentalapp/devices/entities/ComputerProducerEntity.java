package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "computer_procuders")
class ComputerProducerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producer")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String producerName;

    @OneToMany(mappedBy = "computerProducerEntity")//(mappedBy = "computerProducer", cascade = {CascadeType.ALL})
    private List<ComputerModelEntity> computerModelEntities;

    public ComputerProducerEntity(){}
    public ComputerProducerEntity(String producerName) {
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

    public List<ComputerModelEntity> getComputerModels() {
        return computerModelEntities;
    }

    public void setComputerModels(List<ComputerModelEntity> computerModelEntities) {
        this.computerModelEntities = computerModelEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerProducerEntity computerProducerEntity = (ComputerProducerEntity) o;
        return getUuid().equals(computerProducerEntity.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", producerName='" + producerName + '\'' +
                '}';
    }
}
