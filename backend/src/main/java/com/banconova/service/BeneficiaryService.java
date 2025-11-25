
package com.banconova.service;

import com.banconova.domain.entity.Beneficiary;
import com.banconova.domain.entity.User;
import com.banconova.dto.BeneficiaryDto;
import com.banconova.repository.BeneficiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    public List<BeneficiaryDto> list(User user) {
        return beneficiaryRepository.findByOwner(user).stream()
                .map(b -> BeneficiaryDto.builder()
                        .id(b.getId())
                        .name(b.getName())
                        .rut(b.getRut())
                        .bank(b.getBank())
                        .accountType(b.getAccountType())
                        .accountNumber(b.getAccountNumber())
                        .build())
                .collect(Collectors.toList());
    }

    public BeneficiaryDto create(User user, BeneficiaryDto dto) {
        var existing = beneficiaryRepository
                .findByOwnerAndRutAndBankAndAccountNumber(user, dto.getRut(), dto.getBank(), dto.getAccountNumber());
        if (existing.isPresent()) {
            throw new RuntimeException("Beneficiario duplicado");
        }

        Beneficiary b = Beneficiary.builder()
                .owner(user)
                .name(dto.getName())
                .rut(dto.getRut())
                .bank(dto.getBank())
                .accountType(dto.getAccountType())
                .accountNumber(dto.getAccountNumber())
                .build();
        beneficiaryRepository.save(b);

        dto.setId(b.getId());
        return dto;
    }
}
