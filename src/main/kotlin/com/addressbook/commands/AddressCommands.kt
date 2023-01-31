package com.addressbook.commands

import arrow.core.Either
import com.addressbook.Address
import com.addressbook.AddressId
import com.addressbook.CommandContext
import com.addressbook.PersonId
import com.addressbook.requests.*
import com.addressbook.storages.AddressDB
import java.util.*


fun AddAddressRequest.toAddress() =
    Address(
        addressId = UUID.randomUUID(),
        personId = this@toAddress.personId,
        addressType = this@toAddress.addressType,
        addressLine = this@toAddress.addressLine
    )

fun UpdateAddressRequest.toAddress() =
    Address(
        addressId = this@toAddress.addressId,
        personId = this@toAddress.personId,
        addressType = this@toAddress.addressType,
        addressLine = this@toAddress.addressLine
    )

class AddAddressCommand(
    val cmdCtx: CommandContext,
    private val request: AddAddressRequest
): Command {
    override fun execute(): Either<Exception, Address> = AddressDB.addAddress(request.toAddress())
}

class UpdateAddressCommand(
    val cmdCtx: CommandContext,
    private val request: UpdateAddressRequest
) : Command {
    override fun execute(): Either<Exception, Address> = AddressDB.updateAddress(request.toAddress())

}

class RemoveAddressByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
) : Command {
    override fun execute(): Either<Exception, String> = AddressDB.removeAddressByPersonId(personId)
}

class RemoveAddressByAddressIdCommand(
    val cmdCtx: CommandContext,
    private val addressId: AddressId,
) : Command {
    override fun execute(): Either<Exception, String> = AddressDB.removeAddressByAddressId(addressId)
}

class ListAllAddressCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception, Any> = AddressDB.listAllAddress()
}

class ShowAddressByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, Any> = AddressDB.showAddressByPersonId(personId)
}

class ShowAddressByPersonNameCommand(
    val cmdCtx: CommandContext,
    private val personName: String
): Command{
    override fun execute(): Either<Exception, Any> = AddressDB.showAddressByPersonName(personName)
}