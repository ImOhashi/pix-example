package com.ohashi.pixexample.entities;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class Transfer {

    @NotNull
    private String cpf;

    @NotNull
    private PixKey pixKey;

    @NotNull
    private Double value;
}
