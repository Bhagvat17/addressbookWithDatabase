package com.addressbook.commands

import com.addressbook.storages.EmailDB
import com.example.addressbookdb.Email
import com.example.addressbookdb.EmailId
import com.example.addressbookdb.PersonId
import com.example.addressbookdb.requests.AddEmailRequest
import com.example.addressbookdb.requests.UpdateEmailRequest
import java.util.*

fun AddEmailRequest.toEmail() =
    Email(
        emailId = UUID.randomUUID(),
        personId = this@toEmail.personId,
        emailType = this@toEmail.emailType,
        emailAddress = this@toEmail.emailAddress
    )

fun UpdateEmailRequest.toEmail() =
    Email(
        emailId = this@toEmail.emailId,
        personId = this@toEmail.personId,
        emailType = this@toEmail.emailType,
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

class UpdateEmailCommand(
    private val storage: EmailDB,
    private val request: UpdateEmailRequest
) : Command {
    override fun execute(): Email {
        val email = request.toEmail()
        return storage.updateEmail(email)
    }
}

class RemoveEmailByPersonIdCommand(
    private val storage: EmailDB,
    private val personId: PersonId,
) : Command {
    override fun execute(): Any {
        storage.removeEmailByPersonId(personId)
        return " email deleted"
    }
}

class RemoveEmailByEmailIdCommand(
    private val storage: EmailDB,
    private val emailId: EmailId,
) : Command {
    override fun execute(): Any {
        storage.removeEmailByEmailId(emailId)
        return " email deleted"
    }
}