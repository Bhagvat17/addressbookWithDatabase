package com.addressbook.commands

import arrow.core.Either
import com.addressbook.models.CommandContext
import com.addressbook.models.Email
import com.addressbook.models.EmailId
import com.addressbook.models.PersonId
import com.addressbook.requests.AddEmailRequest
import com.addressbook.requests.UpdateEmailRequest
import com.addressbook.repos.EmailRepo
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
    override fun execute(): Either<Exception, Email> = EmailRepo.addEmail(request.toEmail())
}

class UpdateEmailCommand(
    val cmdCtx: CommandContext,
    private val request: UpdateEmailRequest
) : Command {
    override fun execute(): Either<Exception, Email> = EmailRepo.updateEmail(request.toEmail())

}

class RemoveEmailByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
) : Command {
    override fun execute(): Either<Exception,String> = EmailRepo.removeEmailByPersonId(personId)
}

class RemoveEmailByEmailIdCommand(
    val cmdCtx: CommandContext,
    private val emailId: EmailId,
) : Command {
    override fun execute(): Either<Exception,String> = EmailRepo.removeEmailByEmailId(emailId)
}

class fetchAllEmailCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception,List<Email>> = EmailRepo.fetchAllEmail()
}

class fetchEmailByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception,List<Email>> = EmailRepo.fetchEmailByPersonId(personId)
}

class fetchEmailByPersonNameCommand(
    val cmdCtx: CommandContext,
    private val personName: String
): Command{
    override fun execute(): Either<Exception,List<Email>> = EmailRepo.fetchEmailByPersonName(personName)
}