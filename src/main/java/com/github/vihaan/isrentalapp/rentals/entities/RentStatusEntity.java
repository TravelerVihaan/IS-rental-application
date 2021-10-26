package com.github.vihaan.isrentalapp.rentals.entities;

import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rent_status")
public class RentStatusEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;

    @Column(nullable = false, unique = true)
    private String status;

    @OneToMany(mappedBy = "rentStatusEntity")
    private List<ComputerRentalEntity> computerRentalEntities;

    public RentStatusEntity(){}
    public RentStatusEntity(String status) {
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public List<ComputerRentalEntity> getComputerRentals() {
        return computerRentalEntities;
    }

    public void setComputerRentals(List<ComputerRentalEntity> computerRentalEntities) {
        this.computerRentalEntities = computerRentalEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentStatusEntity rentStatusEntity = (RentStatusEntity) o;
        return getUuid().equals(rentStatusEntity.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "RentStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
