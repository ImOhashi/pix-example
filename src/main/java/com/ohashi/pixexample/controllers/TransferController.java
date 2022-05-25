package com.ohashi.pixexample.controllers;

import com.ohashi.pixexample.entities.Transfer;
import com.ohashi.pixexample.entities.dtos.AccountDto;
import com.ohashi.pixexample.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("transfer")
public class TransferController {

    private TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> transferMoney(@RequestBody Transfer transfer,
                                                    UriComponentsBuilder uriComponentsBuilder) throws Exception {
        var account = this.transferService.transferMoney(transfer);

        var accountDto = new AccountDto(account);

        URI uri = uriComponentsBuilder.path("/account/").buildAndExpand(accountDto).toUri();

        return ResponseEntity.created(uri).body(accountDto);
    }
}
