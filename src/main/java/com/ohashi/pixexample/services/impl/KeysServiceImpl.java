package com.ohashi.pixexample.services.impl;

import com.ohashi.pixexample.entities.Account;
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

    @Override
    public PixKey inputKey(String cpf, PixKey newKey) {
        var account = this.accountRepository.getByCpf(cpf);

        account.getListKeys().add(newKey);

        if(!validateKeys(account, newKey)) {
            throw  new IllegalArgumentException("Valor de chave informado, está inválido!");
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

    @Override
    public PixKey inputRandomKey(String cpf) {
        var account = this.accountRepository.getByCpf(cpf);

        var newRandomKey = new PixKey(TypeKey.RANDOM_KEY, UUID.randomUUID().toString());

        account.getListKeys().add(newRandomKey);

        this.accountRepository.save(account);

        return newRandomKey;
    }

    private static boolean validateKeys(Account account, PixKey newKey) {
        if(newKey.getType() == TypeKey.CPF) {
            if (!ValidatePixCpfKey.validateCpfValue(account, newKey.getValue())) {
                return false;
            }
        } else if(newKey.getType() == TypeKey.EMAIL) {
            var isValid = newKey.getValue().matches("^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$");

            if(!isValid) {
                return false;
            }
        } else if(newKey.getType() == TypeKey.TELEFONE) {
            var isValid = newKey.getValue().matches("[\\d]{7}\\-[\\d]{4}");

            if(!isValid) {
                return false;
            }
        }

        return true;
    }
}
