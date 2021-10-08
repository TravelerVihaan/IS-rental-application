package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "producers_computer")
class ComputerProducer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producer")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String producerName;

    @OneToMany(mappedBy = "computerProducer")//(mappedBy = "computerProducer", cascade = {CascadeType.ALL})
    private List<ComputerModel> computerModels;

    public ComputerProducer(){}
    public ComputerProducer(String producerName) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerProducer computerProducer = (ComputerProducer) o;
        return getUuid().equals(computerProducer.getUuid());
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
