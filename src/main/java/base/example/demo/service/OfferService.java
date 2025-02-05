package base.example.demo.service;

//у тебя должен быть класс OfferService
//методы createNewOffer, updateOffer, deleteOffer

import base.example.demo.entity.Offer;
import base.example.demo.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository){
        this.offerRepository = offerRepository;
    }

    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }
}
