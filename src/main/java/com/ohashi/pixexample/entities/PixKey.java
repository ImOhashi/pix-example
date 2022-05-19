package com.ohashi.pixexample.entities;

import com.ohashi.pixexample.enums.TypeKey;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class PixKey implements Serializable {

    private TypeKey type;

    private String value;
}
