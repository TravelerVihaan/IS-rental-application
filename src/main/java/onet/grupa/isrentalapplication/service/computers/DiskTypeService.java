package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.repository.computers.DiskTypeRepository;
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
public class DiskTypeService {

    private DiskTypeRepository diskTypeRepository;

    @Autowired
    public DiskTypeService(DiskTypeRepository diskTypeRepository){
        this.diskTypeRepository = diskTypeRepository;
    }

    public List<DiskType> getAllDisks(){
        return diskTypeRepository.findAll();
    }

    public Optional<DiskType> getDiskTypeById(long id){
        return diskTypeRepository.findById(id);
    }

    /**
     * Add new DiskType entity to database
     *
     * @param diskType Object of computer model generated from JSON incoming from front-end
     *
     * @return HttpStatusEnum with status depending on result of insert entity to DB,
     * can return BADREQUEST if result has errors
     *            CONFLICT if entity has already exists
     *            CREATED if entity was saved in DB
     *
     */
    public HttpStatusEnum addNewDiskType(DiskType diskType){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DiskType>> validationErrors = validator.validate(diskType);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(getDiskTypeByName(diskType.getDiskType()).isPresent())
            return HttpStatusEnum.CONFLICT;
        diskTypeRepository.save(diskType);
        return HttpStatusEnum.CREATED;
    }

    Optional<DiskType> getDiskTypeByName(String diskType){
        return Optional.ofNullable(diskTypeRepository.findByDiskType(diskType));
    }

    public HttpStatusEnum deleteDiskType(Long id){
        if(diskTypeRepository.findById(id).isPresent()){
            diskTypeRepository.deleteById(id);
            return HttpStatusEnum.OK;
        }
        return HttpStatusEnum.BADREQUEST;
    }

}
