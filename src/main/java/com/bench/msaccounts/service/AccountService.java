package com.bench.msaccounts.service;

import com.bench.msaccounts.dto.AccountResponseDTO;
import com.bench.msaccounts.model.Account;
import com.bench.msaccounts.model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public List<AccountResponseDTO> findAll();
    public AccountResponseDTO findByAccountNumber(Long id);
    public Account save(Account user);
    public void delete(Long id);
    public Account update(Long id, Account account);
    }