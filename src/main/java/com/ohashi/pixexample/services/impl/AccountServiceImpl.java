package com.ohashi.pixexample.services.impl;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.repositories.AccountRepository;
import com.ohashi.pixexample.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        if(existsAccount != null) {
            throw new IllegalArgumentException("Conta já cadastrada!");
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
}
