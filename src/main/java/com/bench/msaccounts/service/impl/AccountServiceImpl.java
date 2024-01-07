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
    public AccountResponseDTO findById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException("id: " + id + " does not exist"));
        return accountMapper.toDTO(account);
    }


    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findAll() {
        List<User> userList = Arrays.asList(restTemplate.getForObject("http://localhost:8010/api/v1/users", User[].class));
        List<AccountResponseDTO> accountList = accountRepository
                .findAll()
                .stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());

        for(AccountResponseDTO account: accountList){
            User user = findUserById(userList, account.getUserId());
            if (Objects.nonNull(user)) {
                account.setUser(user);
            }
        }

        return accountList;
    }

    private User findUserById(List<User> usersList, Long userId) {
        for (User user : usersList) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }


    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findByUser() {
//        List<User> userList = Arrays.asList(restTemplate.getForObject("http://localhost:8010/api/v1/users", User[].class));
//        return userList.stream().map(user -> new Account();
        return  null;

    }

    @Transactional(readOnly = false)
    public Account save(Account account) {
//        if (accountRepository.findByUsername(user.getUsername()).isPresent()
//                || userRepository.findByDni((user.getDni())).isPresent()) {
//            throw new UserNotFoundException("Username/Dni was registered");
//        }
//        Account newAccount = new Account().builder()
//                .type(account.getType())
//                .balance(account.getBalance())
//                .state(account.getState())
//                .build();
//        return accountRepository.save(newAccount);
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
}
