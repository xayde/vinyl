package com.vinylstore.vinyl.listener;

import com.vinylstore.vinyl.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.PrePersist;

public class AccountListener {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PrePersist
    public void accountPrePersist(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }
}
