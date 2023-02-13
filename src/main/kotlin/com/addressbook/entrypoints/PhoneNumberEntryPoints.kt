package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.commands.*
import com.addressbook.handlers.PhoneNumberHandler.addPhoneNumberHandler
import com.addressbook.handlers.PhoneNumberHandler.fetchAllPhoneNumberHandler
import com.addressbook.handlers.PhoneNumberHandler.fetchPhoneNumberByPersonIdHandler
import com.addressbook.handlers.PhoneNumberHandler.fetchPhoneNumberByPersonNameHandler
import com.addressbook.handlers.PhoneNumberHandler.removePhoneNumberByPersonIdHandler
import com.addressbook.handlers.PhoneNumberHandler.removePhoneNumberByPhoneNumberIdHandler
import com.addressbook.handlers.PhoneNumberHandler.updatePhoneNumberHandler
import com.addressbook.models.*
import com.addressbook.requests.*

fun addPhoneNumberEntryPoint(
    ac: AppContext,
    req: AddPhoneNumberRequest
): Either<Exception, PhoneNumber> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddPhoneNumberCommand(cmdCtx,req)
    return addPhoneNumberHandler(cmd)
}

fun updatePhoneNumberEntryPoint(
    ac: AppContext,
    req: UpdatePhoneNumberRequest
): Either<Exception, PhoneNumber> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = UpdatePhoneNumberCommand(cmdCtx,req)
    return updatePhoneNumberHandler(cmd)
}


fun removePhoneNumberByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception, String> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemovePhoneNumberByPersonIdCommand(cmdCtx,personId)
    return removePhoneNumberByPersonIdHandler(cmd)
}


fun removePhoneNumberByPhoneNumberIdEntryPoint(
    ac: AppContext,
    phoneNumberId: PhoneNumberId
): Either<Exception, String> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemovePhoneNumberByPhoneNumberIdCommand(cmdCtx,phoneNumberId)
    return removePhoneNumberByPhoneNumberIdHandler(cmd)
}

fun fetchAllPhoneNumberEntryPoint(
    ac: AppContext,
): Either<Exception, List<PhoneNumber>> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchAllPhoneNumberCommand(cmdCtx)
    return fetchAllPhoneNumberHandler(cmd)
}

fun fetchPhoneNumberByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception, List<PhoneNumber>> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchPhoneNumberByPersonIdCommand(cmdCtx,personId)
    return fetchPhoneNumberByPersonIdHandler(cmd)
}

fun fetchPhoneNumberByPersonNameEntryPoint(
    ac: AppContext,
    personName: String,
): Either<Exception, List<PhoneNumber>> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchPhoneNumberByPersonNameCommand(cmdCtx,personName)
    return fetchPhoneNumberByPersonNameHandler(cmd)
}