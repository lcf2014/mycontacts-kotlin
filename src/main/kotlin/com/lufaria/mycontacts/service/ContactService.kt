package com.lufaria.mycontacts.service

import com.lufaria.mycontacts.exception.ResourceNotFoundException
import com.lufaria.mycontacts.model.Contact
import com.lufaria.mycontacts.model.ContactRequest
import com.lufaria.mycontacts.repository.ContactRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContactService(var contactRepository: ContactRepository) {

    fun findById(id: UUID): Contact {
        var contact = contactRepository.findById(id)
        if (contact.isPresent) {
            return contact.get();
        } else {
            throw ResourceNotFoundException("Contact with id %s was not found".format(id));
        }
    }

    fun createContact(contact: ContactRequest): Contact {
        return contactRepository.save(Contact(UUID.randomUUID(), contact.name, contact.email, contact.categoryId))
    }

    fun deleteContact(id: UUID) {
        var contact = contactRepository.findById(id)

        if (contact.isPresent) {
            contactRepository.delete(contact.get());
        } else {
            throw ResourceNotFoundException("Contact with id %s was not found".format(id));
        }
    }
}