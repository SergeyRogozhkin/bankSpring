package base.example.demo.controller;

import base.example.demo.dto.OfferDto;
import base.example.demo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/create")
    public ResponseEntity<OfferDto> addOffer(@RequestBody OfferDto offer) {
        final OfferDto offerDto = offerService.createOffer(offer);
        return new ResponseEntity<>(offerDto, HttpStatus.CREATED);
    }

}
