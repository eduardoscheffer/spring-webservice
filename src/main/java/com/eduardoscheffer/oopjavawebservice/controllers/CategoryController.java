package com.eduardoscheffer.oopjavawebservice.controllers;

import com.eduardoscheffer.oopjavawebservice.entities.Category;
import com.eduardoscheffer.oopjavawebservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Categorys")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {

        List<Category> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category Category = service.findById(id);
        return new ResponseEntity<>(Category, HttpStatus.OK);
    }



}
