package com.banconova.service;

import com.banconova.common.ApiResponse;
import com.banconova.domain.Beneficiary;
import com.banconova.repo.BeneficiaryRepository;
import com.banconova.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficiaryService {
    private final BeneficiaryRepository benRepo;
    private final UserRepository users;

    public List<Beneficiary> list(String usuario){
        var u = users.findByUsuario(usuario).orElseThrow();
        return benRepo.findByUserOrderByAliasAsc(u);
    }

    public ApiResponse<Beneficiary> create(String usuario, Beneficiary b){
        var u = users.findByUsuario(usuario).orElseThrow();
        b.setUser(u);
        return ApiResponse.ok(benRepo.save(b), "Destinatario agregado");
    }

    public ApiResponse<Beneficiary> toggleFav(String usuario, Long id){
        var u = users.findByUsuario(usuario).orElseThrow();
        var b = benRepo.findByIdAndUser(id, u).orElseThrow();
        b.setFavorito(!b.isFavorito());
        return ApiResponse.ok(benRepo.save(b), "Favorito actualizado");
    }
}
