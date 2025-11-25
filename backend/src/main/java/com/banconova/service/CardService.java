
package com.banconova.service;

import com.banconova.domain.entity.Card;
import com.banconova.domain.entity.User;
import com.banconova.domain.enums.CardType;
import com.banconova.dto.CardDto;
import com.banconova.repository.CardRepository;
import com.banconova.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public List<CardDto> getMyCards() {
        User owner = getCurrentUser();
        return cardRepository.findByOwner(owner).stream()
                .map(c -> CardDto.builder()
                        .id(c.getId())
                        .type(c.getType())
                        .maskedNumber(c.getMaskedNumber())
                        .expirationDate(c.getExpirationDate())
                        .build())
                .collect(Collectors.toList());
    }

    public CardDto createDebitCard() {
        User owner = getCurrentUser();
        Card card = Card.builder()
                .owner(owner)
                .type(CardType.DEBITO)
                .maskedNumber(maskNumber(generatePan()))
                .expirationDate(LocalDate.now().plusYears(4))
                .cvvHash("HASHED") // placeholder
                .build();
        cardRepository.save(card);

        return CardDto.builder()
                .id(card.getId())
                .type(card.getType())
                .maskedNumber(card.getMaskedNumber())
                .expirationDate(card.getExpirationDate())
                .build();
    }

    private String generatePan() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String maskNumber(String pan) {
        return "**** **** **** " + pan.substring(pan.length() - 4);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
    }
}
