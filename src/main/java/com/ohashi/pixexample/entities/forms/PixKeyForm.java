package com.ohashi.pixexample.entities.forms;

import com.ohashi.pixexample.enums.TypeKey;
import lombok.Data;
import lombok.Generated;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
@Generated
@Data
public class PixKeyForm implements Serializable {

    @NotNull
    private TypeKey type;

    @NotNull
    private String value;
}