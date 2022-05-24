package com.ohashi.pixexample.services.impl;

import com.ohashi.pixexample.entities.PixKey;
import com.ohashi.pixexample.repositories.AccountRepository;
import com.ohashi.pixexample.services.KeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KeysServiceImpl implements KeysService {

    private final AccountRepository accountRepository;

    @Autowired
    public KeysServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private UUID createRandomKey() {
        return UUID.randomUUID();
    }

    @Override
    public PixKey inputKey(String cpf, PixKey newKey) {
        var account = this.accountRepository.getByCpf(cpf);

        account.getListKeys().add(newKey);

        this.accountRepository.save(account);

        return newKey;
    }

    @Override
    public void deletePixKey(String cpf, PixKey pixKey) {
        var account = this.accountRepository.getByCpf(cpf);

        account.getListKeys().remove(pixKey);

        this.accountRepository.save(account);
    }
}
