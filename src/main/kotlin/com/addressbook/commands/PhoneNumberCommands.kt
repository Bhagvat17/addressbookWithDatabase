package com.addressbook.commands

import arrow.core.Either
import com.addressbook.models.CommandContext
import com.addressbook.models.PersonId
import com.addressbook.models.PhoneNumber
import com.addressbook.models.PhoneNumberId
import com.addressbook.requests.AddPhoneNumberRequest
import com.addressbook.requests.UpdatePhoneNumberRequest
import com.addressbook.repos.PhoneNumberRepo
import java.util.*

fun AddPhoneNumberRequest.toPhoneNumber() =
    PhoneNumber(
        phoneNumberId = UUID.randomUUID(),
        personId = this@toPhoneNumber.personId,
        phoneNumberType = this@toPhoneNumber.phoneNumberType,
        phone = this@toPhoneNumber.phone
    )

fun UpdatePhoneNumberRequest.toPhoneNumber() =
    PhoneNumber(
        phoneNumberId = this@toPhoneNumber.phoneNumberId,
        personId = this@toPhoneNumber.personId,
        phoneNumberType = this@toPhoneNumber.phoneNumberType,
        phone = this@toPhoneNumber.phone
    )

class AddPhoneNumberCommand(
    val cmdCtx: CommandContext,
    private val request: AddPhoneNumberRequest
): Command {
    override fun execute(): Either<Exception, PhoneNumber> = PhoneNumberRepo.addPhoneNumber(request.toPhoneNumber())
}

class UpdatePhoneNumberCommand(
    val cmdCtx: CommandContext,
    private val request: UpdatePhoneNumberRequest
) : Command {
    override fun execute(): Either<Exception, PhoneNumber> = PhoneNumberRepo.updatePhoneNumber(request.toPhoneNumber())

}

class RemovePhoneNumberByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
) : Command {
    override fun execute(): Either<Exception, String> = PhoneNumberRepo.removePhoneNumberByPersonId(personId)
}

class RemovePhoneNumberByPhoneNumberIdCommand(
    val cmdCtx: CommandContext,
    private val phoneNumberId: PhoneNumberId,
) : Command {
    override fun execute(): Either<Exception, String> = PhoneNumberRepo.removePhoneNumberByPhoneNumberId(phoneNumberId)
}

class fetchAllPhoneNumberCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception, List<PhoneNumber>> = PhoneNumberRepo.fetchAllPhoneNumber()
}

class fetchPhoneNumberByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, List<PhoneNumber>> = PhoneNumberRepo.fetchPhoneNumberByPersonId(personId)
}

class fetchPhoneNumberByPersonNameCommand(
    val cmdCtx: CommandContext,
    private val personName: String
): Command{
    override fun execute(): Either<Exception, List<PhoneNumber>> = PhoneNumberRepo.fetchPhoneNumberByPersonName(personName)
}