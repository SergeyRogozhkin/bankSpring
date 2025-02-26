package base.example.demo.mapper;

import base.example.demo.dto.OfferDto;
import base.example.demo.entity.Offer;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {
   public OfferDto toDto(Offer offer) {
       OfferDto offerDto = new OfferDto();
       offerDto.setSum(offer.getSum());
       offerDto.setPercent(offer.getPercent());
       return offerDto;
   }
   public Offer toEntity(OfferDto offerDto) {
       Offer offer = new Offer();
       offer.setSum(offerDto.getSum());
       offer.setPercent(offerDto.getPercent());
       return offer;
   }

}
