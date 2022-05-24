package com.ohashi.pixexample.utils;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.PixKey;
import com.ohashi.pixexample.enums.TypeKey;

public class ValidatePixCpfKey {
    public static boolean validateCpfValue(Account account, String cpf) {
        if (!account.getListKeys().isEmpty()) {
            for (PixKey key : account.getListKeys()) {
                if (key.getType() == TypeKey.CPF) {
                    if (!cpf.equals(account.getCpf())) {
                        return false;
                    }
                }
            }

            /*
                for(int index = 0; newAccount.getListKeys().size() > index; index++) {
                    if (newAccount.getListKeys().get(index).getType() == TypeKey.CPF) {
                        if (!newAccount.getListKeys().get(index).getValue().equals(newAccount.getCpf())) {
                            throw new IllegalArgumentException("CPF informado inválido!");
                        }
                    }
                }
            */
        }

        return true;
    }

    public static boolean validateCpfValue(Account account) {
        if (!account.getListKeys().isEmpty()) {
            for (PixKey key : account.getListKeys()) {
                if (key.getType() == TypeKey.CPF) {
                    if (!key.getValue().equals(account.getCpf())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
