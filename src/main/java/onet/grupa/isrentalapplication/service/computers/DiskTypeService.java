package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.repository.computers.DiskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiskTypeService {

    private DiskTypeRepository diskTypeRepository;

    @Autowired
    public DiskTypeService(DiskTypeRepository diskTypeRepository){
        this.diskTypeRepository = diskTypeRepository;
    }

    public Optional<DiskType> getDiskType(long id){
            return diskTypeRepository.findById(id);
    }

    public List<DiskType> getAllDisks(){
        return diskTypeRepository.findAll();
    }
}
