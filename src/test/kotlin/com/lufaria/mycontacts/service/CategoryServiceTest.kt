package com.lufaria.mycontacts.service

import com.lufaria.mycontacts.model.Category
import com.lufaria.mycontacts.model.CategoryRequest
import com.lufaria.mycontacts.repository.CategoryRepository
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class CategoryServiceTest {

    private var categoryRepository = mockk<CategoryRepository>()

    private var categoryService: CategoryService = CategoryService(categoryRepository);
    private val id: UUID = UUID.randomUUID();
    private val category: Category = Category(id, "Test Category")
    private val categoryRequest: CategoryRequest = CategoryRequest("Test Category")


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun findById() {
        every { categoryRepository.findById(id) } returns Optional.of(category)

        val result = categoryService.findById(id)

        verify(exactly = 1) { categoryRepository.findById(id) }
        assertEquals(category, result)
    }

    @Test
    fun createCategory() {
        every { categoryRepository.save(any()) } returns category

        val result = categoryService.createCategory(categoryRequest)

        verify(exactly = 1) { categoryRepository.save(any()) }
        assertEquals(category, result)

    }

    @Test
    fun deleteCategory() {

        every { categoryRepository.findById(id) } returns Optional.of(category)

        every { categoryRepository.delete(category) } just runs

        categoryService.deleteCategory(id)

        verify(exactly = 1) { categoryRepository.delete(category) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}