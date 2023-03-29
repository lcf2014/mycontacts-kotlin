package com.lufaria.mycontacts.repository

import com.lufaria.mycontacts.model.Contact
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ContactRepository : MongoRepository<Contact, UUID> {
    override fun findById(id: UUID): Optional<Contact>
}