package onet.grupa.isrentalapplication.controller.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.service.rentals.ComputerRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals/computers/")
public class ComputerRentalController {

    private ComputerRentalService computerRentalService;

    @Autowired
    public ComputerRentalController(ComputerRentalService computerRentalService){
        this.computerRentalService = computerRentalService;
    }

    @GetMapping
    public ResponseEntity<List<ComputerRental>> getRentals(@RequestParam(required = false) String searchPhrase){
        if(searchPhrase != null && !searchPhrase.isEmpty())
            return computerRentalService.getResponseWithComputerRentalsAndSearching(searchPhrase);

        return computerRentalService.getResponseWithComputerRentals();
    }

    @GetMapping("rentals/computers/status/{status}")
    public ResponseEntity<List<ComputerRental>> getRentalsWithStatus(@PathVariable String status){
        return computerRentalService.getResponseWithComputerRentalsAndStatus(status);
    }

}
