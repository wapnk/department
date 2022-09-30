package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.model.Department;
import com.example.demo.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class DepartmentService extends CrudService<Department, DepartmentDTO> {

    private final DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo) {
        super(departmentRepo);
        this.departmentRepo = departmentRepo;
    }

    @Override
    public List<Department> getAllSorted(Pageable pageable) {
        return null;
    }
}
