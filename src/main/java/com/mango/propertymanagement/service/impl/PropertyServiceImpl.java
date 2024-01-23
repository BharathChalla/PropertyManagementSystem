package com.mango.propertymanagement.service.impl;

import com.mango.propertymanagement.converter.PropertyConverter;
import com.mango.propertymanagement.dto.PropertyDTO;
import com.mango.propertymanagement.entity.PropertyEntity;
import com.mango.propertymanagement.repository.PropertyRepository;
import com.mango.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyConverter.convertToEntity(propertyDTO);
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
        propertyEntity.setOwnerName(propertyDTO.getOwnerName());
        propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
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
}
