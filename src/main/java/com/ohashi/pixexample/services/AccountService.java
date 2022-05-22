package com.ohashi.pixexample.services;

import com.ohashi.pixexample.entities.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    // Retorno -  Nome do método
    List<Account> listAccounts();
    Account createAccount(Account newAccount);
    Account updateAccount();
    void deleteAccount(String cpf);
    UUID createRandomKey();
}
