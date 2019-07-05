package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.repository.computers.DiskTypeRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DiskTypeService {

    private DiskTypeRepository diskTypeRepository;
    private Validator validator;

    /*
    Constructor with Autowired DiskTypeRepo
     */
    @Autowired
    public DiskTypeService(DiskTypeRepository diskTypeRepository, Validator validator){
        this.diskTypeRepository = diskTypeRepository;
        this.validator = validator;
    }

    /*
    Public methods
     */
    /**
     * Return simple response with list of all found DiskType in database.
     *
     * @return Optional with list of disks
     */
    public Optional<List<DiskType>> getAllDisks(){
        return Optional.ofNullable(diskTypeRepository.findAll());
    }

    /**
     * Return simple response with found DiskType in database.
     *
     * @param id id of DiskType entity
     *
     * @return Optional with DiskType
     */
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
        Set<ConstraintViolation<DiskType>> validationErrors = validator.validate(diskType);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(getDiskTypeByName(diskType.getDiskType()).isPresent())
            return HttpStatusEnum.CONFLICT;
        diskTypeRepository.save(diskType);
        return HttpStatusEnum.CREATED;
    }

    /*
    Private methods
     */
    private Optional<DiskType> getDiskTypeByName(String diskType){
        return Optional.ofNullable(diskTypeRepository.findByDiskType(diskType));
    }

}
