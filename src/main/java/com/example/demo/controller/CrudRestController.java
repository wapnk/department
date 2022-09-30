package com.example.demo.controller;


import com.example.demo.service.CrudService;
import com.example.demo.util.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class CrudRestController<T, TDTO> {

    private final CrudService<T, TDTO> service;

    public CrudRestController(CrudService<T, TDTO> service) {
        this.service = service;
    }

    @GetMapping("") //get resource_path?page=X
    public ResponseEntity readAllPage(@RequestParam(name = "page", required = false) Integer page) {
        List<T> result;
        if (page == null) result = service.readAll();
        else result = service.readAllPage(page);

        if (result != null && !result.isEmpty()) return ResponseEntity.ok().body(result);
        else return ResponseEntity.ok().body("Brak zasobow");
    }

    @GetMapping("{id}") // get resource_path/{id}
    public ResponseEntity readById(@PathVariable("id") Long id) {
        Optional<T> result = service.read(id);
        if (result.isPresent()) return ResponseEntity.ok().body(result);
        else return ResponseEntity.ok().body("Nie istnieje zasob o podanym id");
    }

    @PostMapping("") // post resource_path/
    public ResponseEntity create(@RequestBody TDTO dto) {
        Validator.validate(dto);
        T result = service.createFromDTO(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("{id}") // delete resource_path/{id}
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("test")
    public ResponseEntity test(@RequestParam(required = false, name = "page") Integer page,
                               @RequestParam(required = false, name = "sort") String sort,
                               @RequestParam(required = false, name = "direction") String direction) {

        List<T> result;
        if (page == null && sort == null && direction == null) {
            result = service.readAll();
        } else {//if (page != null) {
            sort = "id";
            direction = "asc";
            result = service.readAllPage(page);
        }
        if (!result.isEmpty()) return ResponseEntity.ok().body(result);
        else return ResponseEntity.ok().body("Brak zasobow");
    }
}
