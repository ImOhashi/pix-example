package com.ohashi.pixexample.services.impl;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.dtos.AccountDto;
import com.ohashi.pixexample.entities.forms.UpdateAccountForm;
import com.ohashi.pixexample.enums.TypeKey;
import com.ohashi.pixexample.repositories.AccountRepository;
import com.ohashi.pixexample.services.AccountService;
import com.ohashi.pixexample.utils.ValidatePixCpfKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AccountServiceImpl implements AccountService {

    // A injeção de dependências via Autowired só é testável se houver um método construtor
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Stream<AccountDto> listAccounts() throws Exception {
        var listAccounts = this.accountRepository.findAll();

        /*
            List<AccountDto> listAccountsDto = null;

            for(int index = 0; listAccounts.size() > index; index++) {
                listAccountsDto.add(new AccountDto(listAccounts.get(index)));
            }
         */

        try {
            return listAccounts.stream().map(AccountDto::new);
        } catch (Exception err) {
            throw new Exception("Não existem contas cadastradas.");
        }
     }

    @Override
    public Account createAccount(Account newAccount) {
        var existsAccount = this.accountRepository.getByCpf(newAccount.getCpf());

        if (existsAccount != null) {
            throw new IllegalArgumentException("Conta já cadastrada!");
        }

        if(!ValidatePixCpfKey.validateCpfValue(newAccount)) {
            throw new IllegalArgumentException("CPF informado inválido!");
        }

        return this.accountRepository.save(newAccount);
    }

    @Override
    public Account updateAccount(String cpf, UpdateAccountForm updateAccountForm) {
        var account = this.accountRepository.getByCpf(cpf);

        if (account == null) {
            throw new IllegalArgumentException("Não existem contas correspondentes ao CPF informado.");
        }

        account.setName(updateAccountForm.getName());

        return this.accountRepository.save(account);
    }

    @Override
    public void deleteAccount(String cpf) {
        var account = this.accountRepository.getByCpf(cpf);

        if (account == null) {
            throw new IllegalArgumentException("Não existem contas correspondentes ao CPF informado.");
        }

        this.accountRepository.deleteByCpf(cpf);
    }
}
