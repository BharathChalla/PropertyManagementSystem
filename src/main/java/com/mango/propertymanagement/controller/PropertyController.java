package com.mango.propertymanagement.controller;

import com.mango.propertymanagement.dto.PropertyDTO;
import com.mango.propertymanagement.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("hello")
    public String sayHello(@RequestParam(name = "name", required = false) String name) {
        if (name == null) name = "World!!!";
        return "Hello my dear " + name;
    }

    @Operation(
            summary = "Save a property",
            description = "This API saves a property to the database."
    )
    @PostMapping("/save")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyDTO = propertyService.saveProperty(propertyDTO);
        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all properties",
            description = "This API gets all properties from the database."
    )

    @GetMapping("/all")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> propertyDTOList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable("userId") Long userId) {
        List<PropertyDTO> propertyDTOList = propertyService.getAllPropertiesForUser(userId);
        return new ResponseEntity<>(propertyDTOList, HttpStatus.OK);
    }

    @Operation(
            summary = "Update a property",
            description = "This API updates a property in the database."
    )
    @PutMapping("/update/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(
            @RequestBody PropertyDTO propertyDTO,
            @PathVariable(name = "propertyId") Long propertyId
    ) {
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Update a property description",
            description = "This API updates a property description in the database."
    )
    @PatchMapping("/update/description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(
            @RequestBody PropertyDTO propertyDTO,
            @PathVariable(name = "propertyId") Long propertyId
    ) {
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Update a property price",
            description = "This API updates a property price in the database."
    )
    @PatchMapping("/update/price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(
            @RequestBody PropertyDTO propertyDTO,
            @PathVariable(name = "propertyId") Long propertyId
    ) {
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a property",
            description = "This API deletes a property from the database."
    )
    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<String> deleteProperty(@PathVariable(name = "propertyId") Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>("Property deleted successfully", HttpStatus.OK);
    }
}
