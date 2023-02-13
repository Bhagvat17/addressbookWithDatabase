package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.models.Address
import com.addressbook.commands.*
object AddressHandler{
    fun addAddressHandler(cmd: AddAddressCommand): Either<Exception, Address> {
        return cmd.execute()
    }

    fun updateAddressHandler(cmd: UpdateAddressCommand): Either<Exception, Address> {
        return cmd.execute()
    }

    fun removeAddressByPersonIdHandler(cmd: RemoveAddressByPersonIdCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun removeAddressByAddressIdHandler(cmd: RemoveAddressByAddressIdCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun fetchAllAddressHandler(cmd: fetchAllAddressCommand): Either<Exception, List<Address>> {
        return cmd.execute()
    }

    fun fetchAddressByPersonIdHandler(cmd: fetchAddressByPersonIdCommand): Either<Exception, List<Address>> {
        return cmd.execute()
    }

    fun fetchAddressByPersonNameHandler(cmd: fetchAddressByPersonNameCommand): Either<Exception, List<Address>> {
        return cmd.execute()
    }
}