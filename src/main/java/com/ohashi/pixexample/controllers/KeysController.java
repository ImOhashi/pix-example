package com.ohashi.pixexample.controllers;

import com.ohashi.pixexample.entities.PixKey;
import com.ohashi.pixexample.entities.forms.PixKeyForm;
import com.ohashi.pixexample.services.KeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("keys")
public class KeysController {

    private final KeysService keysService;

    @Autowired
    public KeysController(KeysService keysService) {
        this.keysService = keysService;
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<PixKey> inputKey(@PathVariable String cpf, @RequestBody @Valid PixKeyForm newKey,
                                           UriComponentsBuilder uriComponentsBuilder) {
        var updatedKey = this.keysService.inputKey(cpf, new PixKey(newKey));

        URI uri = uriComponentsBuilder.path("/account/").buildAndExpand(newKey).toUri();

        return ResponseEntity.created(uri).body(updatedKey);
    }
}
