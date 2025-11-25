
package com.banconova.service;

import com.banconova.domain.entity.Beneficiary;
import com.banconova.domain.entity.User;
import com.banconova.dto.BeneficiaryDto;
import com.banconova.repository.BeneficiaryRepository;
import com.banconova.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final UserRepository userRepository;

    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository, UserRepository userRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.userRepository = userRepository;
    }

    public List<BeneficiaryDto> getMyBeneficiaries() {
        User owner = getCurrentUser();
        return beneficiaryRepository.findByOwner(owner).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public BeneficiaryDto create(BeneficiaryDto dto) {
        User owner = getCurrentUser();

        if (beneficiaryRepository.existsByOwnerAndRutAndAccountNumber(owner, dto.getRut(), dto.getAccountNumber())) {
            throw new IllegalArgumentException("El beneficiario ya existe");
        }

        Beneficiary beneficiary = Beneficiary.builder()
                .owner(owner)
                .name(dto.getName())
                .rut(dto.getRut())
                .bank(dto.getBank())
                .accountType(dto.getAccountType())
                .accountNumber(dto.getAccountNumber())
                .build();

        beneficiaryRepository.save(beneficiary);
        return toDto(beneficiary);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
    }

    private BeneficiaryDto toDto(Beneficiary b) {
        return BeneficiaryDto.builder()
                .id(b.getId())
                .name(b.getName())
                .rut(b.getRut())
                .bank(b.getBank())
                .accountType(b.getAccountType())
                .accountNumber(b.getAccountNumber())
                .build();
    }
}
