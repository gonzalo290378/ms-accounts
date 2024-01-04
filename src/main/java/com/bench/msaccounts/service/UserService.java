package com.bench.msaccounts.service;

import com.bench.msusers.dto.UserResponseDTO;
import com.bench.msusers.model.User;

import java.util.List;

public interface UserService {
    public UserResponseDTO findById(Long id);

    public List<UserResponseDTO> findAll();

    public User save(User user);

    public void delete(Long id);
}