package com.ohashi.pixexample.factories.dtos;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.PixKey;
import com.ohashi.pixexample.entities.dtos.AccountDto;
import com.ohashi.pixexample.enums.TypeKey;

import java.util.List;

public class AccountDtoFactory {
    public static AccountDto sample() {
        Account fakeAccount = new Account();

        fakeAccount.setCpf("333.222.111-00");
        fakeAccount.setName("Dummy");
        fakeAccount.setListKeys(List.of(new PixKey(TypeKey.CPF, "333.222.111-00")));
        fakeAccount.setMoney(300.00);

        return new AccountDto(fakeAccount);
    }

    public static List<AccountDto> sampleList() {
        Account fakeAccount1 = new Account();
        Account fakeAccount2 = new Account();

        fakeAccount1.setCpf("333.222.111-00");
        fakeAccount1.setName("Dummy");
        fakeAccount1.setListKeys(List.of(new PixKey(TypeKey.CPF, "333.222.111-00")));
        fakeAccount1.setMoney(300.00);

        fakeAccount2.setCpf("333.222.111-11");
        fakeAccount2.setName("Dummy 2");
        fakeAccount2.setListKeys(List.of(new PixKey(TypeKey.CPF, "333.222.111-11")));
        fakeAccount2.setMoney(400.00);

        return List.of(new AccountDto(fakeAccount1),
                new AccountDto(fakeAccount2));
    }
}
