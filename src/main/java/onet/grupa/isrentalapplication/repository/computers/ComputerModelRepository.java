package onet.grupa.isrentalapplication.repository.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerModelRepository extends JpaRepository<ComputerModel, Long> {

    ComputerModel findByModel(String modelName);
}
