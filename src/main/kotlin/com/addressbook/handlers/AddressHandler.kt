package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.Address
import com.addressbook.commands.*
object AddressHandler{
    fun addAddressHandler(cmd: AddAddressCommand): Either<Exception, Address> {
        return cmd.execute()
    }

    fun updateAddressHandler(cmd: UpdateAddressCommand): Either<Exception, Address> {
        return cmd.execute()
    }

    fun removeAddressByPersonIdHandler(cmd: RemoveAddressByPersonIdCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun removeAddressByAddressIdHandler(cmd: RemoveAddressByAddressIdCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun listAllAddressEntryPoint(cmd: ListAllAddressCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun showAddressByPersonIdHandler(cmd: ShowAddressByPersonIdCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun showAddressByPersonName(cmd: ShowAddressByPersonNameCommand): Either<Exception, Any> {
        return cmd.execute()
    }
}