package base.example.demo.controller;

import base.example.demo.entity.Offer;
import base.example.demo.repository.OfferRepository;
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
    @Autowired
    private OfferRepository offerRepository;

    @PostMapping("/create")
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
        Offer savedOffer = offerRepository.save(offer);
        return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
    }


}
