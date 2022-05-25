package com.ohashi.pixexample.services.impl;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.PixKey;
import com.ohashi.pixexample.entities.Transfer;
import com.ohashi.pixexample.repositories.AccountRepository;
import com.ohashi.pixexample.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Account transferMoney(Transfer transfer) throws Exception {
        Account existsAccount = null;

        var originAccount = this.accountRepository.getByCpf(transfer.getCpf());

        if(originAccount.getMoney() < transfer.getValue()) {
            throw new IllegalArgumentException("O valor a ser debitado é superior ao valor em conta.");
        }

        var accounts = this.accountRepository.findAll();

        for (Account account : accounts) {
            for (PixKey key : account.getListKeys()) {
                if (key.equals(transfer.getPixKey())) {
                    existsAccount = account;
                }
            }
        }

        if(existsAccount == null) {
            throw new Exception("Chave pix não encontrada!");
        }

        if (existsAccount.getMoney() == null) {
            existsAccount.setMoney(transfer.getValue());
        } else {
            existsAccount.setMoney(transfer.getValue() + existsAccount.getMoney());
        }

        originAccount.setMoney(originAccount.getMoney() - transfer.getValue());

        this.accountRepository.save(originAccount);
        this.accountRepository.save(existsAccount);

        return existsAccount;
    }
}
