package com.addressbook.requests

import com.addressbook.EmailId
import com.addressbook.PersonId

enum class EmailType {
    Home,
    Office
}

data class AddEmailRequest(
    val personId: PersonId,
    val emailType: EmailType,
    val emailAddress: String,
    )

data class UpdateEmailRequest(
    val emailId: EmailId,
    val personId: PersonId,
    val emailType: EmailType,
    val emailAddress: String,
)
