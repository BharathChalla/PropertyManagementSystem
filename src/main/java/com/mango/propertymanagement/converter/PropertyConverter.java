package com.mango.propertymanagement.converter;

import com.mango.propertymanagement.dto.PropertyDTO;
import com.mango.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyConverter {

    public PropertyEntity convertToEntity(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle(propertyDTO.getTitle());
        propertyEntity.setDescription(propertyDTO.getDescription());
        propertyEntity.setPrice(propertyDTO.getPrice());
        propertyEntity.setAddress(propertyDTO.getAddress());
        return propertyEntity;
    }

    public PropertyDTO convertToDTO(PropertyEntity propertyEntity) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(propertyEntity.getId());
        propertyDTO.setTitle(propertyEntity.getTitle());
        propertyDTO.setDescription(propertyEntity.getDescription());
        propertyDTO.setPrice(propertyEntity.getPrice());
        propertyDTO.setAddress(propertyEntity.getAddress());
        return propertyDTO;
    }

    public List<PropertyDTO> convertToDTOList(List<PropertyEntity> propertyEntityList) {
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        for (PropertyEntity propertyEntity : propertyEntityList) {
            propertyDTOList.add(convertToDTO(propertyEntity));
        }
        return propertyDTOList;
    }
}
