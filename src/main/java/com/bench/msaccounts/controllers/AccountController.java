package com.bench.msaccounts.controllers;

import com.bench.msaccounts.dto.AccountResponseDTO;
import com.bench.msaccounts.model.Account;
import com.bench.msaccounts.service.impl.AccountServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("serviceRestTemplate")
    private AccountServiceImpl accountServiceImpl;

    //LOAD BALANCER
    @Value("${config.balanced.test}")
    private String balancerTest;

    //CONFIG-SERVER
    @Value("${configuration.text}")
    private String text;

    @GetMapping()
    public ResponseEntity<List<AccountResponseDTO>> findAll() {
        log.info("Calling findAll with {}");
        return ResponseEntity.ok(accountServiceImpl.findAll());
    }

    @GetMapping("/load-balancer")
    public ResponseEntity<?> loadBalancer() {
        log.info("Calling loadBalancer with {}");
        Map<String, Object> response = new HashMap<>();
        response.put("loadBalancer", balancerTest);
        response.put("users", accountServiceImpl.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> findByAccountNumber(@PathVariable(name = "accountNumber", required = true) Long accountNumber) {
        log.info("Calling findAccountById with {}", accountNumber);
        return ResponseEntity.ok(accountServiceImpl.findByAccountNumber(accountNumber));
    }


    @PostMapping()
    public ResponseEntity<Account> save(@RequestBody @Valid Account account) {
        log.info("Calling save with {}", account);
        return ResponseEntity.ok(accountServiceImpl.save(account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id", required = true) Long id) {
        accountServiceImpl.delete(id);
        log.info("Calling delete with {}", id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @GetMapping("/get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
        log.info("getConfig {}" + " port: " + port);
        Map<String, String> json = new HashMap<>();
        json.put("text", text);
        json.put("port", port);

        if (environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("dev")) {
            json.put("env", environment.getActiveProfiles()[0]);
        }
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

}
