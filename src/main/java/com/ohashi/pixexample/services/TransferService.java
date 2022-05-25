package com.ohashi.pixexample.services;

import com.ohashi.pixexample.entities.Account;
import com.ohashi.pixexample.entities.Transfer;

public interface TransferService {
    Account transferMoney(Transfer transfer) throws Exception;
}
