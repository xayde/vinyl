package com.vinylstore.vinyl.service;

import com.vinylstore.vinyl.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    void createAccount(Account account);
    List<Account> getAllAccounts();
}
