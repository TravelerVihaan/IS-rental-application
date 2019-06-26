package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.repository.computers.DiskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("computers/disks")
public class DiskTypeController {

    private DiskTypeRepository diskTypeRepository;

    @Autowired
    public DiskTypeController(DiskTypeRepository diskTypeRepository){
        this.diskTypeRepository = diskTypeRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DiskType>> getAllDisks(){
        return new ResponseEntity<>(diskTypeRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiskType> getDisk(@PathVariable long id){
        return new ResponseEntity<>(diskTypeRepository.findById(id).orElseGet("????"), new HttpHeaders(), HttpStatus.OK);
    }
}
