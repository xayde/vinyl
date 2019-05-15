package com.vinylstore.vinyl;

import com.vinylstore.vinyl.dao.AccountDao;
import com.vinylstore.vinyl.dto.AccountCreationDto;
import com.vinylstore.vinyl.mapper.AccountCreationMapper;
import com.vinylstore.vinyl.model.Account;
import com.vinylstore.vinyl.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountCreationTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    AccountCreationMapper accountCreationMapper;

    @MockBean
    private AccountDao accountDao;

    @Before
    public  void setUp() {
        AccountCreationDto accountCreationDto = new AccountCreationDto("firstname", "lastname", "test@gmail.com", "password");
        Account account = accountCreationMapper.accountCreationDtoToAccount(accountCreationDto);
        when(accountDao.save(account)).thenReturn(account); ///????
    }

    @Test
    public void createAccountTest() {
        assertNotNull(accountService.getAllAccounts());

    }
}
