package com.ohashi.pixexample.entities.forms;

import lombok.Data;
import lombok.Generated;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Data
@Generated
@Validated
public class UpdateAccountForm {

    @NotNull
    private String name;

    private Double money;
}
