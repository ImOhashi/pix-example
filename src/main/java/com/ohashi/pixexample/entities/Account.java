package com.ohashi.pixexample.entities;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Validated
@Generated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Account")
public class Account implements Serializable {

    @CPF
    @NotNull
    @NotEmpty
    private String cpf;

    @NotNull
    private String name;

    @NotNull
    private List<PixKey> listKeys;
}
