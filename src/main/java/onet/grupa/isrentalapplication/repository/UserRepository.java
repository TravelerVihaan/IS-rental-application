package onet.grupa.isrentalapplication.repository;

import onet.grupa.isrentalapplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
