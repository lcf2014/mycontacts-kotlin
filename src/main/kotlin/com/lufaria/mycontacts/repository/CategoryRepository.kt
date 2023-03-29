package com.lufaria.mycontacts.repository

import com.lufaria.mycontacts.model.Category
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface CategoryRepository : MongoRepository<Category, UUID> {
    override fun findById(id: UUID): Optional<Category>
}