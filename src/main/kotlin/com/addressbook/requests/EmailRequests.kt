package com.addressbook.requests

import com.addressbook.models.EmailId
import com.addressbook.models.PersonId

enum class EmailType {
    Home,
    Office
}

data class AddEmailRequest(
    val personId: PersonId,
    val emailType: String,
    val emailAddress: String,
    )

data class UpdateEmailRequest(
    val emailId: EmailId,
    val personId: PersonId,
    val emailType: String,
    val emailAddress: String,
)
