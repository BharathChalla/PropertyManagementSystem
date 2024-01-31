package com.mango.propertymanagement.service.impl;

import com.mango.propertymanagement.converter.AddressConverter;
import com.mango.propertymanagement.converter.UserConverter;
import com.mango.propertymanagement.dto.UserDTO;
import com.mango.propertymanagement.entity.AddressEntity;
import com.mango.propertymanagement.entity.UserEntity;
import com.mango.propertymanagement.exception.BusinessException;
import com.mango.propertymanagement.exception.ErrorModel;
import com.mango.propertymanagement.repository.AddressRepository;
import com.mango.propertymanagement.repository.UserRepository;
import com.mango.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, AddressRepository addressRepository, AddressConverter addressConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (userEntityOptional.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            errorModelList.add(new ErrorModel(
                    "EMAIL_ALREADY_EXISTS",
                    "The email that you are trying to register already exists"
            ));
            throw new BusinessException(errorModelList);
        }
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        AddressEntity addressEntity = addressConverter.convertUserDTOToAddressEntity(userDTO);
        addressEntity.setUserEntity(userEntity);
        addressEntity = addressRepository.save(addressEntity);

        userDTO = userConverter.convertToDTO(userEntity);
        userDTO = addressConverter.convertAddressEntityToUserDTO(addressEntity, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO loginUser(String email, String password) {
        Optional<UserEntity> userEntityOptional = userRepository.findByOwnerEmailAndPassword(email, password);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return userConverter.convertToDTO(userEntity);
        }
        List<ErrorModel> errorModelList = new ArrayList<>();
        errorModelList.add(new ErrorModel(
                "INVALID_LOGIN",
                "Invalid email or password")
        );
        throw new BusinessException(errorModelList);
    }
}
