package com.addressbook.commands

import arrow.core.Either
import com.addressbook.models.Address
import com.addressbook.models.AddressId
import com.addressbook.models.CommandContext
import com.addressbook.models.PersonId
import com.addressbook.requests.*
import com.addressbook.repos.AddressRepo
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
    override fun execute(): Either<Exception, Address> = AddressRepo.addAddress(request.toAddress())
}

class UpdateAddressCommand(
    val cmdCtx: CommandContext,
    private val request: UpdateAddressRequest
) : Command {
    override fun execute(): Either<Exception, Address> = AddressRepo.updateAddress(request.toAddress())

}

class RemoveAddressByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
) : Command {
    override fun execute(): Either<Exception, String> = AddressRepo.removeAddressByPersonId(personId)
}

class RemoveAddressByAddressIdCommand(
    val cmdCtx: CommandContext,
    private val addressId: AddressId,
) : Command {
    override fun execute(): Either<Exception, String> = AddressRepo.removeAddressByAddressId(addressId)
}

class fetchAllAddressCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception, List<Address>> = AddressRepo.fetchAllAddress()
}

class fetchAddressByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, List<Address>> = AddressRepo.fetchAddressByPersonId(personId)
}

class fetchAddressByPersonNameCommand(
    val cmdCtx: CommandContext,
    private val personName: String
): Command{
    override fun execute(): Either<Exception, List<Address>> = AddressRepo.fetchAddressByPersonName(personName)
}