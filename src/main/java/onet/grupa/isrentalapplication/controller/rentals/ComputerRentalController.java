package onet.grupa.isrentalapplication.controller.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.dto.ComputerRentalDTO;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.rentals.ComputerRentalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rentals/computers")
public class ComputerRentalController {

    private ComputerRentalService computerRentalService;
    private ModelMapper modelMapper;

    @Autowired
    public ComputerRentalController(ComputerRentalService computerRentalService,
                                    ModelMapper modelMapper){
        this.computerRentalService = computerRentalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerRentalDTO>> getRentals(@RequestParam(required = false) String searchPhrase,
                                                              @RequestParam(required = false) String orderBy){
        List<ComputerRentalDTO> computerRentals =
                computerRentalService.getComputerRentals(searchPhrase, orderBy).stream()
                        .map(computerRental -> modelMapper.map(computerRental, ComputerRentalDTO.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(computerRentals);

    }

    @GetMapping(path = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerRentalDTO>> getRentalsWithStatus(@PathVariable String status){
        List<ComputerRentalDTO> computerRentals =
                computerRentalService.getAllComputerRentalsWithStatus(status).stream()
                .map(computerRental -> modelMapper.map(computerRental, ComputerRentalDTO.class))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(computerRentals);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewComputerRental(@RequestBody ComputerRentalDTO computerRentalDTO){
        HttpStatusEnum status = computerRentalService
                .addNewComputerRental(modelMapper.map(computerRentalDTO, ComputerRental.class));
        return HttpStatusEnum.isHttpStatusEquals(status);
    }

}
