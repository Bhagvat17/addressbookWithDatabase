package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.commands.*
import com.addressbook.handlers.AddressHandler.addAddressHandler
import com.addressbook.handlers.AddressHandler.fetchAddressByPersonIdHandler
import com.addressbook.handlers.AddressHandler.fetchAddressByPersonNameHandler
import com.addressbook.handlers.AddressHandler.fetchAllAddressHandler
import com.addressbook.handlers.AddressHandler.removeAddressByAddressIdHandler
import com.addressbook.handlers.AddressHandler.removeAddressByPersonIdHandler
import com.addressbook.handlers.AddressHandler.updateAddressHandler
import com.addressbook.models.*
import com.addressbook.requests.AddAddressRequest
import com.addressbook.requests.UpdateAddressRequest


fun addAddressEntryPoint(
    ac: AppContext,
    req: AddAddressRequest
): Either<Exception, Address> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddAddressCommand(cmdCtx,req)
    return addAddressHandler(cmd)
}

fun updateAddressEntryPoint(
    ac: AppContext,
    req: UpdateAddressRequest
): Either<Exception, Address> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = UpdateAddressCommand(cmdCtx,req)
    return updateAddressHandler(cmd)
}


fun removeAddressByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,String>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveAddressByPersonIdCommand(cmdCtx,personId)
    return removeAddressByPersonIdHandler(cmd)
}


fun removeAddressByAddressIdEntryPoint(
    ac: AppContext,
    addressId: AddressId
): Either<Exception,String>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveAddressByAddressIdCommand(cmdCtx,addressId)
    return removeAddressByAddressIdHandler(cmd)
}

fun fetchAllAddressEntryPoint(
    ac: AppContext,
): Either<Exception,List<Address>>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchAllAddressCommand(cmdCtx)
    return fetchAllAddressHandler(cmd)
}

fun fetchAddressByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,List<Address>>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchAddressByPersonIdCommand(cmdCtx,personId)
    return fetchAddressByPersonIdHandler(cmd)
}

fun fetchAddressByPersonNameEntryPoint(
    ac: AppContext,
    personName: String,
): Either<Exception,List<Address>>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchAddressByPersonNameCommand(cmdCtx,personName)
    return fetchAddressByPersonNameHandler(cmd)
}