package com.bench.msaccounts.service.impl;

import com.bench.msaccounts.dto.AccountResponseDTO;
import com.bench.msaccounts.exceptions.AccountNotFoundException;
import com.bench.msaccounts.mapper.AccountMapper;
import com.bench.msaccounts.model.Account;
import com.bench.msaccounts.model.User;
import com.bench.msaccounts.repositories.AccountRepository;
import com.bench.msaccounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findAll() {
        List<User> userList = Arrays.asList(restTemplate.getForObject("http://localhost:8010/api/v1/users", User[].class));
        List<AccountResponseDTO> accountList = accountRepository.findAll()
                .stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
        return findAccount(accountList, userList);
    }


    @Transactional(readOnly = true)
    public AccountResponseDTO findAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException("id: " + id + " does not exist"));
        User user = findUserById(account.getUserId());
        AccountResponseDTO accountResponseDTO = accountMapper.toDTO(account);
        return findAccount(Arrays.asList(accountResponseDTO), Arrays.asList(user)).get(0);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        HashMap<String, Long> uriPathVariable = new HashMap<>();
        uriPathVariable.put("id", id);
        return restTemplate.getForObject("http://localhost:8010/api/v1/users/{id}", User.class, uriPathVariable);
    }


    @Transactional(readOnly = false)
    public Account save(Account account) {
        return null;
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        Optional<Account> user = accountRepository.findById(id);
        if (!user.isPresent()) {
            throw new AccountNotFoundException("id: " + id + " does not exist");
        }
        userRepository.delete(user.get());
    }

    private List<AccountResponseDTO> findAccount(List<AccountResponseDTO> accountList, List<User> userList) {
        for (AccountResponseDTO account : accountList) {
            userList.stream()
                    .filter(user -> Objects.equals(user.getId(), account.getUserId()))
                    .peek(userResult -> account.setUser(userResult))
                    .collect(Collectors.toList());
        }
        return accountList;
    }


}
