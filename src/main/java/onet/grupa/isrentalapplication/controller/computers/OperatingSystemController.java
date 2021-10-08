package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.devices.entities.OperatingSystem;
import onet.grupa.isrentalapplication.devices.OperatingSystemDTO;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.OperatingSystemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/computers/os")
public class OperatingSystemController {

    private OperatingSystemService operatingSystemService;
    private ModelMapper modelMapper;

    @Autowired
    public OperatingSystemController(OperatingSystemService operatingSystemService, ModelMapper modelMapper){
        this.operatingSystemService = operatingSystemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OperatingSystemDTO>> getAllOperatingSystems(){
        return ResponseEntity.ok(operatingSystemService
                .getAllOperatingSystems()
                .stream()
                .map(os -> modelMapper.map(os, OperatingSystemDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperatingSystemDTO> getOperatingSystem(@PathVariable long id){
        Optional<OperatingSystemDTO> operatingSystemDTO = Optional.ofNullable(modelMapper
            .map(operatingSystemService.getOperatingSystemById(id).orElseThrow(),OperatingSystemDTO.class));
        return ResponseEntity.of(operatingSystemDTO);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOperatingSystem(@RequestBody OperatingSystemDTO operatingSystemDTO){
        OperatingSystem operatingSystem = modelMapper.map(operatingSystemDTO, OperatingSystem.class);
        HttpStatusEnum status = operatingSystemService.addNewOperatingSystem(operatingSystem);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOperatingSystem(@PathVariable Long id){
        HttpStatusEnum status = operatingSystemService.deleteOperatingSystem(id);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }
}
