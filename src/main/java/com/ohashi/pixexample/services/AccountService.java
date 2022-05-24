package com.ohashi.pixexample.services;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.dtos.AccountDto;
import com.ohashi.pixexample.entities.forms.UpdateAccountForm;

import java.util.stream.Stream;

public interface AccountService {
    // Retorno -  Nome do método
    Stream<AccountDto> listAccounts() throws Exception;
    Account createAccount(Account newAccount);
    Account updateAccount(String cpf, UpdateAccountForm name);
    void deleteAccount(String cpf);
}
