package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.dto.DiskTypeDTO;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.DiskTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("computers/disks")
public class DiskTypeController {

    private DiskTypeService diskTypeService;
    private ModelMapper modelMapper;

    @Autowired
    public DiskTypeController(DiskTypeService diskTypeService, ModelMapper modelMapper){
        this.diskTypeService = diskTypeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DiskTypeDTO>> getAllDisks(){
        return ResponseEntity.ok(diskTypeService
                .getAllDisks()
                .stream()
                .map(disk -> modelMapper.map(disk, DiskTypeDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiskTypeDTO> getDisk(@PathVariable long id){
        Optional<DiskTypeDTO> diskTypeDTO = Optional.ofNullable(modelMapper
                .map(diskTypeService.getDiskTypeById(id).orElseThrow(), DiskTypeDTO.class));
        return ResponseEntity.of(diskTypeDTO);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDiskType(@RequestBody DiskTypeDTO diskTypeDTO){
        DiskType diskType = modelMapper.map(diskTypeDTO, DiskType.class);
        HttpStatusEnum status = diskTypeService.addNewDiskType(diskType);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiskType(@PathVariable Long id){
        HttpStatusEnum status = diskTypeService.deleteDiskType(id);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }
}
