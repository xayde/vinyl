package com.vinylstore.vinyl.service;


import com.vinylstore.vinyl.dao.AccountDao;
import com.vinylstore.vinyl.exception.UniqueEmailException;
import com.vinylstore.vinyl.model.Account;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AccountServiceImplTest {
    private AccountServiceImpl sut;
    private AccountDao accountDao;

    @Before
    public void setUp() {
        accountDao = mock(AccountDao.class);
        sut = new AccountServiceImpl(accountDao);
    }

    @Test(expected = UniqueEmailException.class)
    public void uniqueEmailConstraintTest() {
        String email = "test@gmail.com";
        when(accountDao.findByEmail(email)).thenReturn(mock(Account.class));

        Account account = mock(Account.class);
        when(account.getEmail()).thenReturn(email);

        sut.createAccount(account);
    }
}
