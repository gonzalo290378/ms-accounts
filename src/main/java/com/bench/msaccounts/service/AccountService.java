package com.bench.msaccounts.service;

import com.bench.msaccounts.dto.AccountResponseDTO;
import com.bench.msaccounts.model.Account;

import java.util.List;

public interface AccountService {
    public AccountResponseDTO findById(Long id);

    public List<AccountResponseDTO> findAll();

    public Account save(Account user);

    public void delete(Long id);
}