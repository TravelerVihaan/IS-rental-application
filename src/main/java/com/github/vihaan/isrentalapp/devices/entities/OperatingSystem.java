package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "operating_systems")
class OperatingSystem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_os")
    private Long id;

    @Column(name = "operating_system", nullable = false, unique = true)
    private String operatingSystem;

    @OneToMany(mappedBy = "operatingSystem")
    private List<Computer> computers;

    public OperatingSystem(){}
    public OperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatingSystem operatingSystem = (OperatingSystem) o;
        return getUuid().equals(operatingSystem.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "OperatingSystem{" +
                "id=" + id +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}
