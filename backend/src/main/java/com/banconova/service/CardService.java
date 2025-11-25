package com.banconova.service;

import com.banconova.repo.CardRepository;
import com.banconova.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cards;
    private final UserRepository users;

    public Object list(String usuario){
        var u = users.findByUsuario(usuario).orElseThrow();
        return cards.findByUser(u);
    }
}
