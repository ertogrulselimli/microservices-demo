package com.togrul.ms.accounts.service.client;

import com.togrul.ms.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber) {

        CardsDto cardsDto = new CardsDto();
        cardsDto.setCardNumber("Togrul-card-number");
        cardsDto.setCardType("TogrulType");
        return ResponseEntity.ok(cardsDto);
    }
}
