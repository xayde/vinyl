package com.vinylstore.vinyl.service;

import com.vinylstore.vinyl.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    void createAccount(Account account);
    void deleteAccount(Integer userId, String email, String password);
    Account getAccountByUserId(Integer userId);
    Account getAccountByEmail(String email);
}
