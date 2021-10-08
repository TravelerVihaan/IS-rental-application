package com.github.vihaan.isrentalapp.devices.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "operating_systems")
class OperatingSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_os")
    private Long id;

    @NotEmpty
    @Column(name = "operating_system", nullable = false, unique = true)
    private String operatingSystem;

    @OneToMany(mappedBy = "operatingSystem")
    private List<Computer> computers;

    public OperatingSystem(){}
    public OperatingSystem(@NotEmpty String operatingSystem) {
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
    public String toString() {
        return "OperatingSystem{" +
                "id=" + id +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}
