package com.ohashi.pixexample.controllers;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.dtos.AccountDto;
import com.ohashi.pixexample.factories.AccountFactory;
import com.ohashi.pixexample.factories.dtos.AccountDtoFactory;
import com.ohashi.pixexample.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("Test listAccounts - success")
    void testListAccountsSuccess() throws Exception {
        List<AccountDto> mockAccountDto = AccountDtoFactory.sampleList();

        doReturn(mockAccountDto.stream()).when(accountService).listAccounts();

        var response = accountController.listAccounts();

        Assertions.assertEquals(response.getBody().count(), mockAccountDto.stream().count());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Test createAccount - success")
    void testCreateAccountSuccess() {
        Account mockAccount = AccountFactory.sampleWithoutId();
        Account mockAccountWithId = AccountFactory.sampleWithId();
        AccountDto mockAccountDto = AccountDtoFactory.sample();

        var uriInstance = UriComponentsBuilder.newInstance();

        doReturn(mockAccountWithId).when(accountService).createAccount(mockAccount);

        var response = accountController.createAccount(mockAccount, uriInstance);

        Assertions.assertEquals(mockAccountDto.toString(), response.getBody().toString());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
