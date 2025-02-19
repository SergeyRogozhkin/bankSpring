package base.example.demo.service;

import base.example.demo.dto.OfferDto;
import base.example.demo.entity.Offer;
import base.example.demo.mapper.OfferMapper;
import base.example.demo.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    @Autowired
    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
    }

    public OfferDto createOffer(Offer offer) {
        return offerMapper.toDto(offerRepository.save(offer));
    }
}
