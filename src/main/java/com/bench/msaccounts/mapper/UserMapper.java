package com.bench.msaccounts.mapper;

import com.bench.msusers.dto.UserResponseDTO;
import com.bench.msusers.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserResponseDTO toDTO(User user);

    public User toModel(UserResponseDTO userResponseDTO);
}
