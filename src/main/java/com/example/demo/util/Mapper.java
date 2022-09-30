package com.example.demo.util;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.PersonDTO;
import com.example.demo.exceptions.AppException;
import com.example.demo.model.Department;
import com.example.demo.model.Person;
import org.springframework.http.HttpStatus;

public final class Mapper {
    private Mapper() {
    }

    @Deprecated
    public static Department dtoToPOJO(DepartmentDTO dto) {
        return Department.builder()
                .departmentName(dto.getDepartmentName())
                .build();
    }

    @Deprecated
    public static DepartmentDTO pojoToDTO(Department pojo) {
        return DepartmentDTO.builder()
                .departmentName(pojo.getDepartmentName())
                .build();
    }

    @Deprecated
    public static Person dtoToPOJO(PersonDTO dto) {
        return Person.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }

    @Deprecated
    public static PersonDTO pojoToDTO(Person pojo) {
        return PersonDTO.builder()
                .firstName(pojo.getFirstName())
                .lastName(pojo.getLastName())
                .build();
    }

    public static Object dtoToPOJO(Object dto) {
        if (dto instanceof PersonDTO) {
            PersonDTO personDTO = (PersonDTO) dto;
            return dtoToPOJO(personDTO);
        } else if (dto instanceof DepartmentDTO) {
            DepartmentDTO departmentDTO = (DepartmentDTO) dto;
            return dtoToPOJO(departmentDTO);
        } else {
            throw new AppException("Nieznany obiekt DTO", HttpStatus.BAD_REQUEST);
        }
    }

    public static Object pojoToDTO(Object pojo) {
        if (pojo instanceof Person) {
            Person person = (Person) pojo;
            return pojoToDTO(person);
        } else if (pojo instanceof Department) {
            Department department = (Department) pojo;
            return pojoToDTO(department);
        } else {
            throw new AppException("Nieznany obiekt DTO", HttpStatus.BAD_REQUEST);
        }
    }
}
