package com.ohashi.pixexample.services;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.forms.UpdateAccountForm;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    // Retorno -  Nome do método
    List<Account> listAccounts();
    Account createAccount(Account newAccount);
    Account updateAccount(String cpf, UpdateAccountForm name);
    void deleteAccount(String cpf);
    UUID createRandomKey();
}
