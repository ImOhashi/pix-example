package com.ohashi.pixexample.repositories;

import com.ohashi.pixexample.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends MongoRepository<Account, UUID> {
    // Método customizado para retornar uma conta pelo CPF - Método criado via JPA
    Account getByCpf(String cpf);
    void deleteByCpf(String cpf);
}
