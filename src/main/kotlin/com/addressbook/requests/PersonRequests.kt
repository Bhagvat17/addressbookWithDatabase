package com.addressbook.requests

import java.util.UUID

data class AddPersonRequest(
    val firstName: String,
    val lastName: String,
)

data class UpdatePersonRequest(
    val personId: UUID,
    val firstName: String,
    val lastName: String,
)

