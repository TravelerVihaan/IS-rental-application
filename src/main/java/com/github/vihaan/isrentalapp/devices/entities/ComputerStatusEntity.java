package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "computer_statuses")
class ComputerStatusEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer_status")
    private Long id;

    @Column(unique = true, nullable = false)
    private String status;

    @OneToMany(mappedBy = "computerStatusEntity")
    private List<ComputerEntity> computerEntities;

    public ComputerStatusEntity(){}
    public ComputerStatusEntity(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ComputerEntity> getComputers() {
        return computerEntities;
    }

    public void setComputers(List<ComputerEntity> computerEntities) {
        this.computerEntities = computerEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerStatusEntity computerStatusEntity = (ComputerStatusEntity) o;
        return getUuid().equals(computerStatusEntity.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "ComputerStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
