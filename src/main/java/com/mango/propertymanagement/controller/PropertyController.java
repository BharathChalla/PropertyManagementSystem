package com.mango.propertymanagement.controller;

import com.mango.propertymanagement.dto.PropertyDTO;
import com.mango.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("hello")
    public String sayHello(@RequestParam(name = "name", required = false) String name) {
        if (name == null) name = "World!!!";
        return "Hello my dear " + name;
    }

    @PostMapping("/save")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyDTO = propertyService.saveProperty(propertyDTO);
        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> propertyDTOList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyDTOList, HttpStatus.OK);
    }

    @PutMapping("/update/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(
            @RequestBody PropertyDTO propertyDTO,
            @PathVariable(name = "propertyId") Long propertyId
    ) {
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @PatchMapping("/update/description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(
            @RequestBody PropertyDTO propertyDTO,
            @PathVariable(name = "propertyId") Long propertyId
    ) {
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @PatchMapping("/update/price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(
            @RequestBody PropertyDTO propertyDTO,
            @PathVariable(name = "propertyId") Long propertyId
    ) {
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<String> deleteProperty(@PathVariable(name = "propertyId") Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>("Property deleted successfully", HttpStatus.OK);
    }
}
