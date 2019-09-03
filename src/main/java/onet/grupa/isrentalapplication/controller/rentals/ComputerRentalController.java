package onet.grupa.isrentalapplication.controller.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.dto.ComputerRentalDTO;
import onet.grupa.isrentalapplication.service.rentals.ComputerRentalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rentals/computers/")
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
    public ResponseEntity<List<ComputerRentalDTO>> getRentals(@RequestParam(required = false) String searchPhrase, @RequestParam(required = false) String orderBy){
        List<ComputerRental> computerRentals;
        if(searchPhrase != null && !searchPhrase.isEmpty())
            computerRentals = computerRentalService.getAllComputerRentals();
        else
            computerRentals = computerRentalService.getSpecificComputerRentals(searchPhrase,orderBy);

        return ResponseEntity.ok(computerRentals.stream()
                .map(computerRental -> modelMapper.map(computerRental, ComputerRentalDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerRental>> getRentalsWithStatus(@PathVariable String status){
        return ResponseEntity.ok(computerRentalService.getAllComputerRentalsWithStatus(status));
    }

}
