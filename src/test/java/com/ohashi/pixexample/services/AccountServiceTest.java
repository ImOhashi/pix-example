package com.ohashi.pixexample.services;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.dtos.AccountDto;
import com.ohashi.pixexample.entities.forms.UpdateAccountForm;
import com.ohashi.pixexample.factories.dtos.AccountDtoFactory;
import com.ohashi.pixexample.factories.AccountFactory;
import com.ohashi.pixexample.factories.forms.UpdateAccountFormFactory;
import com.ohashi.pixexample.repositories.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Test listAccounts - success")
    void testListAccountsSuccess() throws Exception {
        List<AccountDto> mockAccountDto = AccountDtoFactory.sampleList();

        doReturn(mockAccountDto).when(accountRepository).findAll();

        Stream<AccountDto> accounts = accountService.listAccounts();

        Assertions.assertEquals(accounts.count(), mockAccountDto.size());
    }

    @Test
    @DisplayName("Test listAccounts - exception")
    void testListAccountsException() {
        doReturn(null).when(accountRepository).findAll();

        Assertions.assertThrows(Exception.class, () -> accountService.listAccounts());
    }

    @Test
    @DisplayName("Test createAccount - success")
    void testCreateAccountSuccess() {
        Account mockCreateAccount = AccountFactory.sampleWithoutId();
        Account mockReturnAccount = AccountFactory.sampleWithId();

        doReturn(null).when(accountRepository).getByCpf(mockCreateAccount.getCpf());
        doReturn(mockReturnAccount).when(accountRepository).save(mockCreateAccount);

        var createdAccount = accountService.createAccount(mockCreateAccount);

        Assertions.assertNotNull(createdAccount);
        Assertions.assertEquals(createdAccount.toString(), mockReturnAccount.toString());
    }

    @Test
    @DisplayName("Test createAccount - exception > Conta já cadastrada!")
    void testCreateAccountExceptionExistsAccount() {
        Account mockCreateAccount = AccountFactory.sampleWithoutId();
        Account mockReturnAccount = AccountFactory.sampleWithId();

        doReturn(mockReturnAccount).when(accountRepository).getByCpf(mockCreateAccount.getCpf());

        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.createAccount(mockCreateAccount));
    }

    @Test
    @DisplayName("Test createAccount - exception > CPF informado inválido!")
    void testCreateAccountInvalidCpf() {
        Account mockCreateAccount = AccountFactory.sampleWithPixInvalidKey();

        doReturn(null).when(accountRepository).getByCpf(mockCreateAccount.getCpf());

        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.createAccount(mockCreateAccount));
    }

    @Test
    @DisplayName("Test updateAccount - success")
    void testUpdateAccountSuccess() {
        Account mockCreateAccount = AccountFactory.sampleWithId();
        Account mockUpdatedAccount = AccountFactory.sampleWithIdUpdated();

        UpdateAccountForm mockUpdateForm = UpdateAccountFormFactory.sample();

        doReturn(mockCreateAccount).when(accountRepository).getByCpf(mockCreateAccount.getCpf());
        doReturn(mockUpdatedAccount).when(accountRepository).save(mockUpdatedAccount);

        var updatedAccount = accountService.updateAccount(mockCreateAccount.getCpf(), mockUpdateForm);

        Assertions.assertEquals(updatedAccount.toString(), mockUpdatedAccount.toString());
    }
}
