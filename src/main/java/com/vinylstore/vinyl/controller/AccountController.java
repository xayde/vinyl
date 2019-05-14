package com.vinylstore.vinyl.controller;


import com.vinylstore.vinyl.dto.AccountCreationDto;
import com.vinylstore.vinyl.mapper.AccountCreationMapper;
import com.vinylstore.vinyl.model.Account;
import com.vinylstore.vinyl.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountCreationMapper accountCreationMapper;

    @PostMapping(path = "/users", produces = "application/json")
    public ResponseEntity<?> addNewAccount(@RequestBody AccountCreationDto accountCreationDTO) {

        if (accountCreationDTO == null) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        Account account = accountCreationMapper.accountCreationDtoToAccount(accountCreationDTO);

        if (accountService.findByEmail(accountCreationDTO.getEmail().trim()) != null) {
            return new ResponseEntity<>("Email address already in use", HttpStatus.FORBIDDEN);
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        for (ConstraintViolation<Account> violation : violations) {
            return new ResponseEntity<>(violation.getMessage(), HttpStatus.BAD_REQUEST);
        }

        accountService.createAccount(accountCreationMapper.accountCreationDtoToAccount(accountCreationDTO));

        return new ResponseEntity<>("Account has been successfully created", HttpStatus.OK);
    }
}
