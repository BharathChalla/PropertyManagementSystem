package com.mango.propertymanagement.service;

import com.mango.propertymanagement.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO loginUser(String email, String password);
}
