package com.lufaria.mycontacts.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.lufaria.mycontacts.model.Category
import com.lufaria.mycontacts.model.CategoryRequest
import com.lufaria.mycontacts.service.CategoryService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


@ContextConfiguration(classes = arrayOf(CategoryController::class))
@WebMvcTest
class CategoryControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var categoryService: CategoryService
    private var id = UUID.randomUUID();
    private var category: Category = Category(id, "Test Name")

    private var categoryRequest: CategoryRequest = CategoryRequest("Test Name")

    @Test
    fun getCategoryById() {
        every { categoryService.findById(id) } returns category

        mockMvc.get("/categories/%s".format(id))
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
    fun createCategory() {
        every { categoryService.createCategory(categoryRequest) } returns category

        mockMvc.perform(post("/categories")
                .content(asJsonString(categoryRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    fun deleteCategory() {
        every { categoryService.deleteCategory(id) } just runs

        mockMvc.delete("/categories/%s".format(id))
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