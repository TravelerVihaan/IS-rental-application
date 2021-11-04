package com.github.vihaan.isrentalapp.users.entities;

import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;
import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id",
                    referencedColumnName = "id_user")},
            inverseJoinColumns = {@JoinColumn(name="role_id",
                    referencedColumnName = "id_role")})
    private Set<UserRoleEntity> roleEntities;

    @OneToMany(mappedBy = "whoSetStatus")
    private List<ComputerRentalEntity> computerRentals;

    public UserEntity(){}
    public UserEntity(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public List<ComputerRentalEntity> getComputerRentals() {
        return computerRentals;
    }

    public void setComputerRentals(List<ComputerRentalEntity> computerRentalEntities) {
        this.computerRentals = computerRentalEntities;
    }

    public Set<UserRoleEntity> getRoles() {
        return roleEntities;
    }

    public void setRoles(Set<UserRoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return getUuid().equals(userEntity.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roles=" + roleEntities +
                '}';
    }
}
