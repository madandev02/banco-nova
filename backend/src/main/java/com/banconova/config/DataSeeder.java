package com.banconova.config;

import com.banconova.domain.*;
import com.banconova.domain.enums.*;
import com.banconova.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserRepository users;
    private final AccountRepository accounts;
    private final MovementRepository movements;
    private final BeneficiaryRepository beneficiaries;
    private final CardRepository cards;
    private final InvestmentRepository investments;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if(users.count()>0) return;

        User demo = User.builder()
                .nombre("Cliente Demo")
                .usuario("12.345.678-9")
                .email("demo@banconova.cl")
                .telefono("+56 9 1234 5678")
                .passwordHash(encoder.encode("1234"))
                .role(Role.USER)
                .build();
        users.save(demo);

        Account a1 = Account.builder().user(demo).tipo(AccountType.CORRIENTE).numero("12345678").moneda(Currency.CLP).saldo(new BigDecimal("980000")).build();
        Account a2 = Account.builder().user(demo).tipo(AccountType.VISTA).numero("99887766").moneda(Currency.CLP).saldo(new BigDecimal("270000")).build();
        Account a3 = Account.builder().user(demo).tipo(AccountType.AHORRO).numero("44556677").moneda(Currency.CLP).saldo(new BigDecimal("1250000")).build();
        accounts.saveAll(List.of(a1,a2,a3));

        movements.save(Movement.builder().account(a1).descripcion("Transferencia recibida - Juan Pérez").monto(new BigDecimal("50000")).tipo(MovementType.ABONO).estado(MovementStatus.OK).build());
        movements.save(Movement.builder().account(a1).descripcion("Pago tarjeta crédito").monto(new BigDecimal("30000")).tipo(MovementType.CARGO).estado(MovementStatus.OK).build());
        movements.save(Movement.builder().account(a1).descripcion("Compra Amazon").monto(new BigDecimal("14990")).tipo(MovementType.CARGO).estado(MovementStatus.PENDIENTE).build());

        beneficiaries.save(Beneficiary.builder().user(demo).alias("Mamá").nombre("Ana Narváez").rut("11.111.111-1").banco("Banco Nova").tipoCuenta("Cuenta Vista").numeroCuenta("77778888").favorito(true).build());
        beneficiaries.save(Beneficiary.builder().user(demo).alias("Amigo Juan").nombre("Juan Pérez").rut("22.222.222-2").banco("Santander").tipoCuenta("Cuenta Corriente").numeroCuenta("12312312").favorito(false).build());

        cards.save(Card.builder().user(demo).tipo(CardType.DEBITO).numeroMasked("**** 4455").activa(true).build());
        cards.save(Card.builder().user(demo).tipo(CardType.CREDITO).numeroMasked("**** 9988").cupo(new BigDecimal("1200000")).utilizado(new BigDecimal("420000")).activa(true).build());

        investments.save(Investment.builder().user(demo).tipo("Depósito a Plazo").monto(new BigDecimal("500000")).rentabilidadAnual(6.2).vencimiento("2026-02-01").build());
        investments.save(Investment.builder().user(demo).tipo("Fondo Conservador").monto(new BigDecimal("300000")).rentabilidadAnual(4.1).vencimiento("Sin vencimiento").build());
    }
}
