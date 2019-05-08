package com.vinylstore.vinyl.controller;

import com.vinylstore.vinyl.dao.AccountDao;
import com.vinylstore.vinyl.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountDao accountDao;

    @RequestMapping(path = "/")
    public @ResponseBody
    String addNewUser() {
        Account account = new Account();
        account.setFirstName("madalina");
        account.setLastName("diana");

        accountDao.save(account);
        return "Saved";
    }

        @GetMapping(path = "/all")
        public @ResponseBody
        Iterable<Account> getAllAccounts() {
            return accountDao.findAll();
        }

}
