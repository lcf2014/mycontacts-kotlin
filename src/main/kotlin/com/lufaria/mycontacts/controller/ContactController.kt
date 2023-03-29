package com.lufaria.mycontacts.controller

import com.lufaria.mycontacts.model.ContactRequest
import com.lufaria.mycontacts.service.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/")
class ContactController(@Autowired val service: ContactService) {

    @GetMapping("contacts/{id}")
    fun getContactById(@PathVariable("id") id: UUID): ResponseEntity<Any?> {
        return ResponseEntity<Any?>(service.findById(id), HttpStatus.OK)
    }

    @PostMapping("contacts")
    fun createContact(@RequestBody contact: ContactRequest): ResponseEntity<Any?> {
        return ResponseEntity<Any?>(service.createContact(contact), HttpStatus.CREATED)
    }

    @DeleteMapping("contacts/{id}")
    fun deleteContact(@PathVariable("id") id: UUID): ResponseEntity<Any?> {
        service.deleteContact(id);
        return ResponseEntity<Any?>(HttpStatus.NO_CONTENT)
    }
}