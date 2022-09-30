package com.example.demo.service;

import com.example.demo.exceptions.AppException;
import com.example.demo.util.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import javax.persistence.OptimisticLockException;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public abstract class CrudService<T, TDTO> {
    private static int PAGE_SIZE = 20;
    private JpaRepository<T, Long> repository;

    public CrudService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T createFromDTO(TDTO tdto) {
        T pojo = (T) Mapper.dtoToPOJO(tdto);
        return repository.save(pojo);
    }

    public T create(T t) {
        return repository.save(t);
    }


    public Optional<T> read(Long id) {
        return repository.findById(id);
    }

    public List<T> readAll() {
        return repository.findAll();
    }

    public List<T> readAllPage(int page) {
        if (page <= 0) page = 1;
        Page<T> result = repository.findAll(PageRequest.of(page - 1, PAGE_SIZE));
        return result.getContent();
    }

    public T update(T t) {
        Optional<T> dbObject = repository.findById(1L);
        if (dbObject.isEmpty()) throw new AppException("Object doesnt exist");
        try {
            return repository.save(t);
        } catch (OptimisticLockException e) {
            T hibernateObject = t;
            System.out.println(dbObject.get().equals(hibernateObject));
            return dbObject.get();
        }
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public void deleteById(Long id) {
        if (exist(id)) throw new AppException("Zasob nie istnieje", HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }

    private boolean exist(Long id) {
        return repository.findById(id).isEmpty();
    }

    public abstract List<T> getAllSorted(Pageable pageable);
}
