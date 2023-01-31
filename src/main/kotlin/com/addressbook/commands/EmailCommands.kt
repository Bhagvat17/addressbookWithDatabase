package com.addressbook.commands

import arrow.core.Either
import com.addressbook.CommandContext
import com.addressbook.Email
import com.addressbook.EmailId
import com.addressbook.PersonId
import com.addressbook.requests.AddEmailRequest
import com.addressbook.requests.UpdateEmailRequest
import com.addressbook.storages.EmailDB
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
    val cmdCtx: CommandContext,
    private val request: AddEmailRequest
): Command {
    override fun execute(): Either<Exception, Email> = EmailDB.addEmail(request.toEmail())
}

class UpdateEmailCommand(
    val cmdCtx: CommandContext,
    private val request: UpdateEmailRequest
) : Command {
    override fun execute(): Either<Exception, Email> = EmailDB.updateEmail(request.toEmail())

}

class RemoveEmailByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
) : Command {
    override fun execute(): Either<Exception,String> = EmailDB.removeEmailByPersonId(personId)
}

class RemoveEmailByEmailIdCommand(
    val cmdCtx: CommandContext,
    private val emailId: EmailId,
) : Command {
    override fun execute(): Either<Exception,String> = EmailDB.removeEmailByEmailId(emailId)
}

class ListAllEmailCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception,Any> = EmailDB.listAllEmail()
}

class ShowEmailByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception,Any> = EmailDB.showEmailByPersonId(personId)
}

class ShowEmailByPersonNameCommand(
    val cmdCtx: CommandContext,
    private val personName: String
): Command{
    override fun execute(): Either<Exception,Any> = EmailDB.showEmailByPersonName(personName)
}