package com.vinylstore.vinyl.mapper;

import com.vinylstore.vinyl.dto.AccountCreationDto;
import com.vinylstore.vinyl.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountCreationMapper {
    AccountCreationDto accountToAccountCreationDto(Account account);
    Account  accountCreationDtoToAccount(AccountCreationDto accountCreationDto);
}
