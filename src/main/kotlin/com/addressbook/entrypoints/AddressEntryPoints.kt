package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.*
import com.addressbook.commands.*
import com.addressbook.requests.AddAddressRequest
import com.addressbook.requests.UpdateAddressRequest


fun addAddressEntryPoint(
    ac: AppContext,
    req: AddAddressRequest
): Either<Exception, Address> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddAddressCommand(cmdCtx,req)
    return cmd.execute()
}

fun updateAddressEntryPoint(
    ac: AppContext,
    req: UpdateAddressRequest
): Either<Exception, Address> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = UpdateAddressCommand(cmdCtx,req)
    return cmd.execute()
}


fun removeAddressByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,String>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveAddressByPersonIdCommand(cmdCtx,personId)
    return cmd.execute()
}


fun removeAddressByAddressIdEntryPoint(
    ac: AppContext,
    addressId: AddressId
): Either<Exception,String>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveAddressByAddressIdCommand(cmdCtx,addressId)
    return cmd.execute()
}

fun listAllAddressEntryPoint(
    ac: AppContext,
): Either<Exception,Any>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = ListAllAddressCommand(cmdCtx)
    return cmd.execute()
}

fun showAddressByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,Any>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = ShowAddressByPersonIdCommand(cmdCtx,personId)
    return cmd.execute()
}

fun showAddressByPersonNameEntryPoint(
    ac: AppContext,
    personName: String,
): Either<Exception,Any>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = ShowAddressByPersonNameCommand(cmdCtx,personName)
    return cmd.execute()
}