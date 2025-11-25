
package com.banconova.service;

import com.banconova.domain.entity.Card;
import com.banconova.domain.entity.User;
import com.banconova.domain.enums.CardType;
import com.banconova.dto.CardDto;
import com.banconova.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardDto> list(User user) {
        return cardRepository.findByOwner(user).stream()
                .map(c -> CardDto.builder()
                        .id(c.getId())
                        .maskedNumber(c.getMaskedNumber())
                        .type(c.getType().name())
                        .expirationDate(c.getExpirationDate())
                        .build())
                .collect(Collectors.toList());
    }

    public CardDto createDebitCard(User user, String last4) {
        String masked = "**** **** **** " + last4;
        Card c = Card.builder()
                .owner(user)
                .type(CardType.DEBITO)
                .maskedNumber(masked)
                .expirationDate(LocalDate.now().plusYears(4))
                .cvvHash("HASHED")
                .build();
        cardRepository.save(c);

        return CardDto.builder()
                .id(c.getId())
                .maskedNumber(c.getMaskedNumber())
                .type(c.getType().name())
                .expirationDate(c.getExpirationDate())
                .build();
    }
}
