package com.example.addressbookdb.requests

import com.example.addressbookdb.EmailId
import com.example.addressbookdb.PersonId

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
