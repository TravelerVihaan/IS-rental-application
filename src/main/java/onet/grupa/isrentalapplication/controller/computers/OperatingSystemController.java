package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.OperatingSystem;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.OperatingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computers/os")
public class OperatingSystemController {

    private OperatingSystemService operatingSystemService;

    @Autowired
    public OperatingSystemController(OperatingSystemService operatingSystemService){
        this.operatingSystemService = operatingSystemService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OperatingSystem>> getAllOperatingSystems(){
        return ResponseEntity.of(operatingSystemService.getAllOperatingSystems());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperatingSystem> getOperatingSystem(@PathVariable long id){
        return ResponseEntity.of(operatingSystemService.getOperatingSystemById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOperatingSystem(@RequestBody OperatingSystem operatingSystem){
        HttpStatusEnum status = operatingSystemService.addNewOperatingSystem(operatingSystem);

        return HttpStatusEnum.isHttpStatusEquals(status);
    }
}
