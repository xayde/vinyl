package com.vinylstore.vinyl.controller;


import com.vinylstore.vinyl.dto.AccountCreationDto;
import com.vinylstore.vinyl.dto.AccountRemovalDto;
import com.vinylstore.vinyl.mapper.AccountCreationMapper;
import com.vinylstore.vinyl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountCreationMapper accountCreationMapper;

    @PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewAccount(@RequestBody AccountCreationDto accountCreationDto) {

        if (accountCreationDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        accountService.createAccount(accountCreationMapper.accountCreationDtoToAccount(accountCreationDto));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/users/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> deleteAccount(@PathVariable("userId") Integer userId, @RequestBody AccountRemovalDto accountRemovalDto) {

        if (userId == null || accountRemovalDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        accountService.deleteAccount(userId, accountRemovalDto.getEmail().trim(), accountRemovalDto.getPassword().trim());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
