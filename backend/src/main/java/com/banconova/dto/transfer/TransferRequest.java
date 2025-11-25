package com.banconova.dto.transfer;
import jakarta.validation.constraints.*;
import lombok.Getter; import lombok.Setter;
import java.math.BigDecimal;
@Getter @Setter
public class TransferRequest {
    @NotNull private Long cuentaOrigenId;
    @NotBlank private String bancoDestino;
    @NotBlank private String rut;
    @NotBlank private String nombre;
    @NotBlank private String tipoCuenta;
    @NotBlank private String numeroCuenta;
    @NotNull @DecimalMin("1000.0") private BigDecimal monto;
    private String mensaje;
}
