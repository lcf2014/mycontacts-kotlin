package com.lufaria.mycontacts.model

import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("contacts")
class Contact(
        @Id
        val id: UUID = UUID.randomUUID(),

        @NotNull
        val name: String,

        @NotNull
        val email: String,

        @NotNull
        val categoryId: UUID
)