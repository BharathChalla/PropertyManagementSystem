package com.mango.propertymanagement.service.impl;

import com.mango.propertymanagement.converter.PropertyConverter;
import com.mango.propertymanagement.dto.PropertyDTO;
import com.mango.propertymanagement.entity.PropertyEntity;
import com.mango.propertymanagement.entity.UserEntity;
import com.mango.propertymanagement.exception.BusinessException;
import com.mango.propertymanagement.exception.ErrorModel;
import com.mango.propertymanagement.repository.PropertyRepository;
import com.mango.propertymanagement.repository.UserRepository;
import com.mango.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyConverter propertyConverter;
    private final UserRepository userRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyConverter propertyConverter, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyConverter = propertyConverter;
        this.userRepository = userRepository;
    }

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(propertyDTO.getUserId());
        if (optionalUserEntity.isEmpty()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            errorModelList.add(new ErrorModel(
                    "USER_ID_NOT_EXISTS",
                    "User ID does not exist in the system"
            ));
            throw new BusinessException(errorModelList);
        }
        PropertyEntity propertyEntity = propertyConverter.convertToEntity(propertyDTO);
        propertyEntity.setUserEntity(optionalUserEntity.get());
        propertyEntity = propertyRepository.save(propertyEntity);
        return propertyConverter.convertToDTO(propertyEntity);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository.findAll();
        return propertyConverter.convertToDTOList(propertyEntityList);
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntityOptional = propertyRepository.findById(propertyId);
        if (propertyEntityOptional.isEmpty()) {
            return null;
        }
        PropertyEntity propertyEntity = propertyEntityOptional.get();
        propertyEntity.setTitle(propertyDTO.getTitle());
        propertyEntity.setDescription(propertyDTO.getDescription());
        propertyEntity.setPrice(propertyDTO.getPrice());
        propertyEntity.setAddress(propertyDTO.getAddress());
        propertyEntity = propertyRepository.save(propertyEntity);

        return propertyConverter.convertToDTO(propertyEntity);
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntityOptional = propertyRepository.findById(propertyId);
        if (propertyEntityOptional.isEmpty()) {
            return null;
        }
        PropertyEntity propertyEntity = propertyEntityOptional.get();
        propertyEntity.setDescription(propertyDTO.getDescription());
        propertyEntity = propertyRepository.save(propertyEntity);
        return propertyConverter.convertToDTO(propertyEntity);
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntityOptional = propertyRepository.findById(propertyId);
        if (propertyEntityOptional.isEmpty()) {
            return null;
        }
        PropertyEntity propertyEntity = propertyEntityOptional.get();
        propertyEntity.setPrice(propertyDTO.getPrice());
        propertyEntity = propertyRepository.save(propertyEntity);
        return propertyConverter.convertToDTO(propertyEntity);
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> listOfProps = propertyRepository.findAllByUserEntityId(userId);
        return propertyConverter.convertToDTOList(listOfProps);
    }
}
