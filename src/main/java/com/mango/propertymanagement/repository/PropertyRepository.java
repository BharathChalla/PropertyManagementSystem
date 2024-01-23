package com.mango.propertymanagement.repository;

import com.mango.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}
