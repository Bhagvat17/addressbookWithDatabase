package com.addressbook.commands

import arrow.core.Either
import com.addressbook.*
import com.addressbook.requests.AddPhoneNumberRequest
import com.addressbook.requests.UpdatePhoneNumberRequest
import com.addressbook.storages.PhoneNumberDB
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
    override fun execute(): Either<Exception, PhoneNumber> = PhoneNumberDB.addPhoneNumber(request.toPhoneNumber())
}

class UpdatePhoneNumberCommand(
    val cmdCtx: CommandContext,
    private val request: UpdatePhoneNumberRequest
) : Command {
    override fun execute(): Either<Exception, PhoneNumber> = PhoneNumberDB.updatePhoneNumber(request.toPhoneNumber())

}

class RemovePhoneNumberByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
) : Command {
    override fun execute(): Either<Exception, String> = PhoneNumberDB.removePhoneNumberByPersonId(personId)
}

class RemovePhoneNumberByPhoneNumberIdCommand(
    val cmdCtx: CommandContext,
    private val phoneNumberId: PhoneNumberId,
) : Command {
    override fun execute(): Either<Exception, String> = PhoneNumberDB.removePhoneNumberByPhoneNumberId(phoneNumberId)
}

class ListAllPhoneNumberCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception, Any> = PhoneNumberDB.listAllPhoneNumber()
}

class ShowPhoneNumberByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, Any> = PhoneNumberDB.showPhoneNumberByPersonId(personId)
}

class ShowPhoneNumberByPersonNameCommand(
    val cmdCtx: CommandContext,
    private val personName: String
): Command{
    override fun execute(): Either<Exception, Any> = PhoneNumberDB.showPhoneNumberByPersonName(personName)
}