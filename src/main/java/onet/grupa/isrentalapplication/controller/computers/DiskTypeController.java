package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.service.computers.DiskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private DiskTypeService diskTypeService;

    @Autowired
    public DiskTypeController(DiskTypeService diskTypeService){
        this.diskTypeService = diskTypeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DiskType>> getAllDisks(){
        return diskTypeService.getResponseWithAllDisks();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiskType> getDisk(@PathVariable long id){
        return diskTypeService.getResponseWithDisk(id);
    }
}