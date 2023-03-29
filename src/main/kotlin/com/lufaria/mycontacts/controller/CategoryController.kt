package com.lufaria.mycontacts.controller

import com.lufaria.mycontacts.model.CategoryRequest
import com.lufaria.mycontacts.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/")
class CategoryController(@Autowired val service: CategoryService) {

    @GetMapping("categories/{id}")
    fun getCategoryById(@PathVariable("id") id: UUID): ResponseEntity<Any?> {
        return ResponseEntity<Any?>(service.findById(id), HttpStatus.OK)
    }

    @PostMapping("categories")
    fun createCategory(@RequestBody category: CategoryRequest): ResponseEntity<Any?> {
        return ResponseEntity<Any?>(service.createCategory(category), HttpStatus.CREATED)
    }

    @DeleteMapping("categories/{id}")
    fun deleteCategory(@PathVariable("id") id: UUID): ResponseEntity<Any?> {
        service.deleteCategory(id)
        return ResponseEntity<Any?>(HttpStatus.NO_CONTENT)
    }
}