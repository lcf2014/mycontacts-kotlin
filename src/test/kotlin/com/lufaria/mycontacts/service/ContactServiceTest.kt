package com.lufaria.mycontacts.service

import com.lufaria.mycontacts.model.Contact
import com.lufaria.mycontacts.model.ContactRequest
import com.lufaria.mycontacts.repository.ContactRepository
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class ContactServiceTest {

    private var contactRepository = mockk<ContactRepository>()

    private var contactService: ContactService = ContactService(contactRepository);
    private val id: UUID = UUID.randomUUID();
    private val contact: Contact = Contact(id, "Test Contact", "email@email.com", id)
    private val contactRequest: ContactRequest = ContactRequest("Test Contact", "email@email.com", id)


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun findById() {
        every { contactRepository.findById(id) } returns Optional.of(contact)

        val result = contactService.findById(id)

        verify(exactly = 1) { contactRepository.findById(id) }
        assertEquals(contact, result)
    }

    @Test
    fun createContact() {
        every { contactRepository.save(any()) } returns contact

        val result = contactService.createContact(contactRequest)

        verify(exactly = 1) { contactRepository.save(any()) }
        assertEquals(contact, result)

    }

    @Test
    fun deleteContact() {

        every { contactRepository.findById(id) } returns Optional.of(contact)

        every { contactRepository.delete(contact) } just runs

        contactService.deleteContact(id)

        verify(exactly = 1) { contactRepository.delete(contact) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}