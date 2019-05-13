package com.vinylstore.vinyl.service;

import com.vinylstore.vinyl.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    void createAccount(Account account);
}
