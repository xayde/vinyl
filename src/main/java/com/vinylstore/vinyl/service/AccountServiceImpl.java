package com.vinylstore.vinyl.service;

import com.vinylstore.vinyl.dao.AccountDao;
import com.vinylstore.vinyl.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public void createAccount(Account account) {
        accountDao.save(account);
    }

    @Override
    public Account findByEmail(String email) {
       return accountDao.findByEmail(email);
    }
}
