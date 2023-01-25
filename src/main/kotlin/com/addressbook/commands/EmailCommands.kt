package com.addressbook.commands

import com.addressbook.storages.EmailDB
import com.example.addressbook.Email
import com.example.addressbook.requests.AddEmailRequest
import java.util.*

fun AddEmailRequest.toEmail() =
    Email(
        emailId = UUID.randomUUID(),
        personId = this@toEmail.personId,
        emailType = this@toEmail.type,
        emailAddress = this@toEmail.emailAddress
    )

class AddEmailCommand(
    private val storage: EmailDB,
    private val request: AddEmailRequest
): Command {
    override fun execute(): Email {
        val email = request.toEmail()


        val emailDetail = EmailDB.addEmail(email)

        return Email(
            emailId= emailDetail.emailId,
            personId = emailDetail.personId,
            emailType = emailDetail.emailType,
            emailAddress =emailDetail.emailAddress
        )
    }
}