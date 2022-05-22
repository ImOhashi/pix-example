package com.ohashi.pixexample.controllers;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.dtos.AccountDto;
import com.ohashi.pixexample.entities.forms.UpdateAccountForm;
import com.ohashi.pixexample.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Stream;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Stream<AccountDto>> listAccounts() throws Exception {
        return ResponseEntity.ok(this.accountService.listAccounts());
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody Account newAccount, UriComponentsBuilder uriComponentsBuilder) {
        Account createdAccount = this.accountService.createAccount(newAccount);

        URI uri = uriComponentsBuilder.path("/account/").buildAndExpand(createdAccount.getCpf()).toUri();

        return ResponseEntity.created(uri).body(new AccountDto(createdAccount));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String cpf, @Valid @RequestBody UpdateAccountForm updateAccountForm,
                                                 UriComponentsBuilder uriComponentsBuilder) {
        var updatedAccount = this.accountService.updateAccount(cpf, updateAccountForm);

        URI uri = uriComponentsBuilder.path("/account/").buildAndExpand(updatedAccount.getCpf()).toUri();

        return ResponseEntity.created(uri).body(new AccountDto(updatedAccount));
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByCpf(@PathVariable String cpf) {
        this.accountService.deleteAccount(cpf);
    }
}
