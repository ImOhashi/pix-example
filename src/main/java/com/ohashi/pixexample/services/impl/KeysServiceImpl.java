package com.ohashi.pixexample.services.impl;

import com.ohashi.pixexample.entities.PixKey;
import com.ohashi.pixexample.enums.TypeKey;
import com.ohashi.pixexample.repositories.AccountRepository;
import com.ohashi.pixexample.services.KeysService;
import com.ohashi.pixexample.utils.ValidatePixCpfKey;
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

        if(newKey.getType() == TypeKey.CPF) {
            if (!ValidatePixCpfKey.validateCpfValue(account, newKey.getValue())) {
                throw new IllegalArgumentException("CPF informado inválido!");
            }
        }

        this.accountRepository.save(account);

        return newKey;
    }

    @Override
    public void deletePixKey(String cpf, PixKey pixKey) throws Exception {
        var account = this.accountRepository.getByCpf(cpf);

        var isRemoved = account.getListKeys().remove(pixKey);

        if(!isRemoved) {
            throw  new Exception("Chave pix não encontrada.");
        }

        this.accountRepository.save(account);
    }
}
