package com.ohashi.pixexample.entities.dtos;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.PixKey;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Generated
@Data
public class AccountDto {

    public AccountDto(Account account) {
        this.cpf = account.getCpf();
        this.name = account.getName();
        this.listKeys = account.getListKeys();
        this.money = account.getMoney();
    }

    private String cpf;

    private String name;

    private List<PixKey> listKeys;

    private Double money;
}
