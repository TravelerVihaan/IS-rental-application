package onet.grupa.isrentalapplication.controller.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals/computers/")
public class ComputerRentalController {

    @GetMapping
    public ResponseEntity<ComputerRental> getAllRentals(){
        return null;
    }
}
