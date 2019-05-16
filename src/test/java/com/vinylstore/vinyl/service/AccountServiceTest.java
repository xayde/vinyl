package com.vinylstore.vinyl.service;


import com.vinylstore.vinyl.dao.AccountDao;
import com.vinylstore.vinyl.exception.UniqueEmailException;
import com.vinylstore.vinyl.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    private AccountServiceImpl sut;
    @Mock
    private AccountDao accountDao;
    @Mock
    private Account account;

    @Before
    public void setUp() {
        sut = new AccountServiceImpl(accountDao);
    }

    @Test(expected = UniqueEmailException.class)
    public void uniqueEmailConstraintTest() {
        String email = "test1@gmail.com";
        when(accountDao.findByEmail(email)).thenReturn(account);

        when(account.getEmail()).thenReturn(email);

        sut.createAccount(account);
    }
}
