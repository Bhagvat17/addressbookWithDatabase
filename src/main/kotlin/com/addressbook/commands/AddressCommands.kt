package com.addressbook.commands


import com.addressbook.storages.AddressDB
import com.example.addressbookdb.Address
import com.example.addressbookdb.AddressId
import com.example.addressbookdb.PersonId
import com.example.addressbookdb.requests.AddAddressRequest
import com.example.addressbookdb.requests.UpdateAddressRequest
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
    private val storage: AddressDB,
    private val request: AddAddressRequest
): Command {
    override fun execute(): Address {
        val address = request.toAddress()


        val addressDetail = AddressDB.addAddress(address)

        return Address(
            addressId = addressDetail.addressId,
            personId = addressDetail.personId,
            addressType = addressDetail.addressType,
            addressLine =addressDetail.addressLine
        )
    }
}

class UpdateAddressCommand(
    private val storage: AddressDB,
    private val request: UpdateAddressRequest
) : Command {
    override fun execute(): Address {
        val address = request.toAddress()
        return storage.updateAddress(address)

    }
}

class RemoveAddressByPersonIdCommand(
    private val storage: AddressDB,
    private val personId: PersonId,
) : Command {
    override fun execute(): Any {
        storage.removeAddressByPersonID(personId)
        return " address deleted"
    }
}

class RemoveAddressByAddressIdCommand(
    private val storage: AddressDB,
    private val addressId: AddressId,
) : Command {
    override fun execute(): Any {
        storage.removeAddressByAddressId(addressId)
        return " address deleted"
    }
}


class ListAllAddressCommand(
    private val storage: AddressDB
): Command{
    override fun execute(): Any {
        return storage.showAllAddress()
    }
}

class ShowAddressByPersonIdCommand(
    private val storage: AddressDB,
    private val personId: PersonId
): Command{
    override fun execute(): Any {
        return storage.showAddressByPersonId(personId)
    }
}

class ShowAddressByPersonNameCommand(
    private val storage: AddressDB,
    private val personName: String
): Command{
    override fun execute(): Any {
        return storage.showAddressByPersonName(personName)
    }
}