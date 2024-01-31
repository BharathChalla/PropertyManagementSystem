package com.mango.propertymanagement.converter;

import com.mango.propertymanagement.dto.UserDTO;
import com.mango.propertymanagement.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {
    public AddressEntity convertUserDTOToAddressEntity(UserDTO userDTO) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNumber(userDTO.getHouseNumber());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setState(userDTO.getState());
        addressEntity.setZipCode(userDTO.getZipCode());
        addressEntity.setCountry(userDTO.getCountry());
        return addressEntity;
    }

    public UserDTO convertAddressEntityToUserDTO(AddressEntity addressEntity, UserDTO userDTO) {
        if (userDTO == null) {
            userDTO = new UserDTO();
        }
        userDTO.setHouseNumber(addressEntity.getHouseNumber());
        userDTO.setStreet(addressEntity.getStreet());
        userDTO.setCity(addressEntity.getCity());
        userDTO.setState(addressEntity.getState());
        userDTO.setZipCode(addressEntity.getZipCode());
        userDTO.setCountry(addressEntity.getCountry());
        return userDTO;
    }
}
