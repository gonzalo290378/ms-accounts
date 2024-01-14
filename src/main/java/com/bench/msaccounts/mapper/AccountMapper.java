package com.bench.msaccounts.mapper;

import com.bench.msaccounts.dto.AccountResponseDTO;
import com.bench.msaccounts.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    public AccountResponseDTO toDTO(Account account);
    public Account toModel(AccountResponseDTO accountResponseDTO);
}
