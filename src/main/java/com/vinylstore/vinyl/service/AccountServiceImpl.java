package com.vinylstore.vinyl.service;

import com.vinylstore.vinyl.dao.AccountDao;
import com.vinylstore.vinyl.exception.EmailOrPasswordNotExistsException;
import com.vinylstore.vinyl.exception.PasswordDoesNotMatchException;
import com.vinylstore.vinyl.exception.UniqueEmailException;
import com.vinylstore.vinyl.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createAccount(Account account) {
        if (getAccountByEmail(account.getEmail().trim()) != null) {
            throw new UniqueEmailException();
        }
        accountDao.save(account);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountDao.findByEmail(email);
    }

    @Override
    public void deleteAccount(Integer userId, String email, String password) {
        Account account = getAccountByUserId(userId);
        if (account != null && account.getEmail().equalsIgnoreCase(email)) {
            if (passwordEncoder.matches(password, account.getPassword())) {
                accountDao.deleteById(userId);
            } else {
                throw new PasswordDoesNotMatchException("Email address or/and password are invalid");
            }
        } else {
            throw new EmailOrPasswordNotExistsException("Email address or/and password are invalid");
        }
    }

    @Override
    public Account getAccountByUserId(Integer userId) {
        return accountDao.findAccountById(userId);
    }


}
