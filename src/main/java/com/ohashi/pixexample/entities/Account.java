package com.ohashi.pixexample.entities;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.ohashi.pixexample.entities.forms.UpdateAccountForm;
import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
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

    @Id
    private ObjectId _id;

    @CPF
    @NotNull
    @NotEmpty
    private String cpf;

    @NotNull
    private String name;

    @NotNull
    private List<PixKey> listKeys;

    private Double money;
}
