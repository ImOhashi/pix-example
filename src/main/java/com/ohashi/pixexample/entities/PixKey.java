package com.ohashi.pixexample.entities;

import com.ohashi.pixexample.entities.forms.PixKeyForm;
import com.ohashi.pixexample.enums.TypeKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class PixKey implements Serializable {

    public PixKey(PixKeyForm pixKeyForm) {
        this.type = pixKeyForm.getType();
        this.value = pixKeyForm.getValue();
    }

    private TypeKey type;
    private String value;
}
