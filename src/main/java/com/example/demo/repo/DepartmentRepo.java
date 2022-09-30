package com.example.demo.repo;

import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {


    @Query("select d from Department d")
//    @Query("select distinct d from Department d join fetch d.personList dp")
    List<Department> findAllSql();
}
