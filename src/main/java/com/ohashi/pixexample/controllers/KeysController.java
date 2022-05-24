package com.ohashi.pixexample.controllers;

import com.ohashi.pixexample.entities.PixKey;
import com.ohashi.pixexample.entities.forms.PixKeyForm;
import com.ohashi.pixexample.services.KeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKey(@PathVariable String cpf, @RequestBody @Valid PixKeyForm pixKey) throws Exception {
        this.keysService.deletePixKey(cpf, new PixKey(pixKey));
    }

    @PutMapping("/random/{cpf}")
    public ResponseEntity<PixKey> inputRandomKey(@PathVariable String cpf, UriComponentsBuilder uriComponentsBuilder) {
        var createdRandomKey = this.keysService.inputRandomKey(cpf);

        URI uri = uriComponentsBuilder.path("/account/").buildAndExpand(createdRandomKey).toUri();

        return ResponseEntity.created(uri).body(createdRandomKey);
    }
}
