package com.ohashi.pixexample.factories;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.PixKey;
import com.ohashi.pixexample.enums.TypeKey;
import org.bson.types.ObjectId;

import java.util.List;

public class AccountFactory {
    public static Account sampleWithId() {
        Account fakeAccount = new Account();

        fakeAccount.set_id(new ObjectId("628c6233018e87310b594cc4"));
        fakeAccount.setCpf("333.222.111-00");
        fakeAccount.setName("Dummy");
        fakeAccount.setListKeys(List.of(new PixKey(TypeKey.CPF, "333.222.111-00")));
        fakeAccount.setMoney(300.00);

        return fakeAccount;
    }

    public static Account sampleWithoutId() {
        Account fakeAccount = new Account();

        fakeAccount.setCpf("333.222.111-00");
        fakeAccount.setName("Dummy");
        fakeAccount.setListKeys(List.of(new PixKey(TypeKey.CPF, "333.222.111-00")));
        fakeAccount.setMoney(300.00);

        return fakeAccount;
    }

    public static Account sampleWithIdUpdated() {
        Account fakeAccount = new Account();

        fakeAccount.set_id(new ObjectId("628c6233018e87310b594cc4"));
        fakeAccount.setCpf("333.222.111-00");
        fakeAccount.setName("Dummyzinho");
        fakeAccount.setListKeys(List.of(new PixKey(TypeKey.CPF, "333.222.111-00")));
        fakeAccount.setMoney(300.00);

        return fakeAccount;
    }

    public static Account sampleWithPixInvalidKey() {
        Account fakeAccount = new Account();

        fakeAccount.setCpf("333.222.111-00");
        fakeAccount.setName("Dummy");
        fakeAccount.setListKeys(List.of(new PixKey(TypeKey.CPF, "333.222.111-99")));
        fakeAccount.setMoney(300.00);

        return fakeAccount;
    }
}
