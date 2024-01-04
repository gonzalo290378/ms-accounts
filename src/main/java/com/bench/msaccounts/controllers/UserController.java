package com.bench.msaccounts.controllers;

import com.bench.msusers.dto.UserResponseDTO;
import com.bench.msusers.model.User;
import com.bench.msusers.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private Environment environment;

    //CONFIG-SERVER
    @Value("${configuration.text}")
    private String text;

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        log.info("Calling findAll with {}");
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable(name = "id", required = true) Long id) {
        log.info("Calling findById with {}", id);
        return ResponseEntity.ok(userServiceImpl.findById(id));
    }


    @PostMapping()
    public ResponseEntity<User> save(@RequestBody @Valid User user) {
        log.info("Calling save with {}", user);
        return ResponseEntity.ok(userServiceImpl.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id", required = true) Long id) {
        userServiceImpl.delete(id);
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
