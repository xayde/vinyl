package com.vinylstore.vinyl.controller;


import com.vinylstore.vinyl.dto.AccountCreationDto;
import com.vinylstore.vinyl.mapper.AccountCreationMapper;
import com.vinylstore.vinyl.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountCreationMapper accountCreationMapper;

    @PostMapping(path = "/users", produces = "application/json")
    public ResponseEntity<?> addNewAccount(@RequestBody AccountCreationDto accountCreationDto) {

        if (accountCreationDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        accountService.createAccount(accountCreationMapper.accountCreationDtoToAccount(accountCreationDto));

        return new ResponseEntity<>("Account has been successfully created", HttpStatus.OK);
    }
}
