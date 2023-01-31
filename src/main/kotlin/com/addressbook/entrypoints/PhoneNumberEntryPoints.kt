package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.*
import com.addressbook.commands.*
import com.addressbook.requests.*

fun addPhoneNumberEntryPoint(
    ac: AppContext,
    req: AddPhoneNumberRequest
): Either<Exception, PhoneNumber> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddPhoneNumberCommand(cmdCtx,req)
    return cmd.execute()
}

fun updatePhoneNumberEntryPoint(
    ac: AppContext,
    req: UpdatePhoneNumberRequest
): Either<Exception, PhoneNumber> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = UpdatePhoneNumberCommand(cmdCtx,req)
    return cmd.execute()
}


fun removePhoneNumberByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception, String> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemovePhoneNumberByPersonIdCommand(cmdCtx,personId)
    return cmd.execute()
}


fun removePhoneNumberByPhoneNumberIdEntryPoint(
    ac: AppContext,
    phoneNumberId: PhoneNumberId
): Either<Exception, String> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemovePhoneNumberByPhoneNumberIdCommand(cmdCtx,phoneNumberId)
    return cmd.execute()
}

fun listAllPhoneNumberEntryPoint(
    ac: AppContext,
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ListAllPhoneNumberCommand(cmdCtx)
    return cmd.execute()
}

fun showPhoneNumberByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ShowPhoneNumberByPersonIdCommand(cmdCtx,personId)
    return cmd.execute()
}

fun showPhoneNumberByPersonNameEntryPoint(
    ac: AppContext,
    personName: String,
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ShowPhoneNumberByPersonNameCommand(cmdCtx,personName)
    return cmd.execute()
}