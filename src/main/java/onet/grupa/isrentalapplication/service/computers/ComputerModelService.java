package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerModel;
import onet.grupa.isrentalapplication.repository.computers.ComputerModelRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ComputerModelService {

    private ComputerModelRepository computerModelRepository;

    @Autowired
    public ComputerModelService(ComputerModelRepository computerModelRepository){
        this.computerModelRepository = computerModelRepository;
    }

    public List<ComputerModel> getAllComputerModels(){
        return computerModelRepository.findAll();
    }

    public Optional<ComputerModel> getComputerModel(long id){
        return computerModelRepository.findById(id);
    }

    Optional<ComputerModel> getComputerModelByName(String modelName){
        return Optional.ofNullable(computerModelRepository.findByModel(modelName));
    }

    /**
     * Add new computer model to database
     *
     * @param computerModel Object of computer model generated from JSON incoming from front-end
     *
     * @return HttpStatusEnum with status depending on result of insert entity to DB,
     * can return BAD_REQUEST if result has errors
     *            CONFLICT if entity has already exists
     *            CREATED if entity was saved in DB
     *
     */
    public HttpStatusEnum addNewComputerModel(ComputerModel computerModel){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ComputerModel>> validationErrors = validator.validate(computerModel);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(getComputerModelByName(computerModel.getModel()).isPresent())
            return HttpStatusEnum.CONFLICT;

        computerModelRepository.save(computerModel);
        return HttpStatusEnum.CREATED;
    }

    public HttpStatusEnum deleteComputerModel(Long id){
        if(getComputerModel(id).isPresent()){
            computerModelRepository.deleteById(id);
            return HttpStatusEnum.OK;
        }
        return HttpStatusEnum.BADREQUEST;
    }
}
