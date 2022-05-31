package com.ohashi.pixexample.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Data
@Generated
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountForm {

    @NotNull
    private String name;

    private Double money;
}
