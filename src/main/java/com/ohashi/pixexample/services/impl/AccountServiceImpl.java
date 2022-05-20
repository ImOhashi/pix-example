package com.ohashi.pixexample.services.impl;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.enums.TypeKey;
import com.ohashi.pixexample.repositories.AccountRepository;
import com.ohashi.pixexample.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    // A injeção de dependências via Autowired só é testável se houver um método construtor
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> listAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account newAccount) {
        var existsAccount = this.accountRepository.getByCpf(newAccount.getCpf());

        if (existsAccount != null) {
            throw new IllegalArgumentException("Conta já cadastrada!");
        }

        if (!newAccount.getListKeys().isEmpty()) {
            newAccount.getListKeys().forEach(key -> {
                if (key.getType() == TypeKey.CPF) {
                    if (!key.getValue().equals(newAccount.getCpf())) {
                        throw new IllegalArgumentException("CPF informado inválido!");
                    }
                }
            });

            /*
                for(int index = 0; newAccount.getListKeys().size() > index; index++) {
                    if (newAccount.getListKeys().get(index).getType() == TypeKey.CPF) {
                        if (!newAccount.getListKeys().get(index).getValue().equals(newAccount.getCpf())) {
                            throw new IllegalArgumentException("CPF informado inválido!");
                        }
                    }
                }
            */
        }

        return this.accountRepository.save(newAccount);
    }

    @Override
    public Account updateAccount() {
        return null;
    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public UUID createRandomKey() {
        return null;
    }
}
