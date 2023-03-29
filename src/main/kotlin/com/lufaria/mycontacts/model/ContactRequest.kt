package com.lufaria.mycontacts.model

import org.jetbrains.annotations.NotNull
import java.util.*

data class ContactRequest(
        @NotNull val name: String,
        @NotNull val email: String,
        @NotNull val categoryId: UUID
)