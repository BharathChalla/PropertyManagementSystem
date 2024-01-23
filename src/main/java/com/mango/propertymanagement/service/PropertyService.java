package com.mango.propertymanagement.service;

import com.mango.propertymanagement.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {
    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();

    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);

    PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId);

    PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId);

    void deleteProperty(Long propertyId);
}
