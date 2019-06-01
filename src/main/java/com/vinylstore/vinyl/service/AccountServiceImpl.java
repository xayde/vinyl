package com.vinylstore.vinyl.service;

import com.vinylstore.vinyl.dao.AccountDao;
import com.vinylstore.vinyl.exception.EmailOrPasswordNotExistsException;
import com.vinylstore.vinyl.exception.PasswordDoesNotMatchException;
import com.vinylstore.vinyl.exception.UniqueEmailException;
import com.vinylstore.vinyl.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AccountDao accountDao;

    @Override
    public void createAccount(Account account) {
        if (getAccountByEmail(account.getEmail().trim()) != null) {
            throw new UniqueEmailException("Email address already exists.");
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

        if (account == null || !account.getEmail().equalsIgnoreCase(email)) {
            throw new EmailOrPasswordNotExistsException("Email does not exist!");
        }
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new PasswordDoesNotMatchException("Password does not match!");
        }
        accountDao.deleteById(userId);
    }

    @Override
    public Account getAccountByUserId(Integer userId) {
        return accountDao.findAccountById(userId);
    }
}
