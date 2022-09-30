package com.example.demo.util;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.PersonDTO;
import com.example.demo.exceptions.AppException;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;

public final class Validator {
    private static Logger log;

    private Validator() {
    }

    static {
        log = Logger.getLogger("Validator");
    }

    private static void validate(DepartmentDTO dto) {
        if (dto == null) {
            throw new AppException("Obiket nie moze byc pusty");
        }
        String name = dto.getDepartmentName();
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new AppException("Wymagane sa wszystkie pola");
        }
    }

    private static void validate(PersonDTO dto) {
        if (dto == null) {
            throw new AppException("Obiket nie moze byc pusty");
        }
        String firstName = dto.getFirstName();
        if (firstName == null || firstName.isEmpty() || firstName.isBlank()) {
            throw new AppException("Wymagane sa wszystkie pola");
        }
        String lastName = dto.getLastName();
        if (lastName == null || lastName.isEmpty() || lastName.isBlank()) {
            throw new AppException("Wymagane sa wszystkie pola");
        }
    }

    public static void validate(Object dto) {
        if (dto instanceof DepartmentDTO) {
            DepartmentDTO departmentDTO = (DepartmentDTO) dto;
            validate(departmentDTO);
        } else if (dto instanceof PersonDTO) {
            PersonDTO personDTO = (PersonDTO) dto;
            validate(personDTO);
        } else {
            throw new AppException("Nieznany obiekt DTO", HttpStatus.BAD_REQUEST);
        }
    }
}
