package com.lufaria.mycontacts.service

import com.lufaria.mycontacts.exception.ResourceNotFoundException
import com.lufaria.mycontacts.model.Category
import com.lufaria.mycontacts.model.CategoryRequest
import com.lufaria.mycontacts.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService(var categoryRepository: CategoryRepository) {
    fun findById(id: UUID): Category {
        var category = categoryRepository.findById(id)
        if (category.isPresent) {
            return category.get();
        } else {
            throw ResourceNotFoundException("Category with id %s was not found".format(id));
        }
    }

    fun createCategory(categoryRequest: CategoryRequest) : Category {
    return categoryRepository.save(Category(UUID.randomUUID(), categoryRequest.name))
}

fun deleteCategory(id: UUID) {
    var category = categoryRepository.findById(id)

    if (category.isPresent) {
        categoryRepository.delete(category.get());
    } else {
        throw ResourceNotFoundException("Category with id %s was not found".format(id));
    }
}
}