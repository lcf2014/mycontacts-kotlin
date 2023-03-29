package com.lufaria.mycontacts.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.lufaria.mycontacts.model.Contact
import com.lufaria.mycontacts.model.ContactRequest
import com.lufaria.mycontacts.service.ContactService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@WebMvcTest
@ContextConfiguration(classes = arrayOf(ContactController::class))
class ContactControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var contactService: ContactService
    private var categoryId = UUID.randomUUID()
    private var id = UUID.randomUUID();
    private var contact: Contact = Contact(id, "Test Name", "email@email.com", categoryId)
    private var contactRequest: ContactRequest = ContactRequest("Test Name", "email@email.com", categoryId)

    @Test
    fun getContactById() {
        every { contactService.findById(id) } returns contact

        mockMvc.get("/contacts/%s".format(id))
                .andExpect {
                    status {
                        isOk()
                    }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                    }
                    jsonPath("$.size()", Matchers.greaterThan(0))
                }
    }

    @Test
    fun createContact() {
        every { contactService.createContact(contactRequest) } returns contact

        mockMvc.perform(MockMvcRequestBuilders.post("/contacts")
                .content(asJsonString(contact))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }


    @Test
    fun deleteContact() {
        every { contactService.deleteContact(id) } just runs

        mockMvc.delete("/contacts/%s".format(id))
                .andExpect {
                    status {
                        isNoContent()
                    }
                }
    }

    private fun asJsonString(obj: Any): String {
        return try {
            ObjectMapper().writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}