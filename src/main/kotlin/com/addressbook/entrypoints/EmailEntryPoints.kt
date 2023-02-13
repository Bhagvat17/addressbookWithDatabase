package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.commands.*
import com.addressbook.handlers.EmailHandler.addEmailHandler
import com.addressbook.handlers.EmailHandler.fetchAllEmailHandler
import com.addressbook.handlers.EmailHandler.fetchEmailByPersonIdHandler
import com.addressbook.handlers.EmailHandler.fetchEmailByPersonNameHandler
import com.addressbook.handlers.EmailHandler.removeEmailByEmailIdHandler
import com.addressbook.handlers.EmailHandler.removeEmailByPersonIdHandler
import com.addressbook.handlers.EmailHandler.updateEmailHandler
import com.addressbook.models.*
import com.addressbook.requests.AddEmailRequest
import com.addressbook.requests.UpdateEmailRequest

fun addEmailEntryPoint(
    ac: AppContext,
    req: AddEmailRequest
): Either<Exception, Email> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddEmailCommand(cmdCtx,req)
    return addEmailHandler(cmd)
}

fun updateEmailEntryPoint(
    ac: AppContext,
    req: UpdateEmailRequest
): Either<Exception, Email> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = UpdateEmailCommand(cmdCtx,req)
    return updateEmailHandler(cmd)
}


fun removeEmailByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,String>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveEmailByPersonIdCommand(cmdCtx,personId)
    return removeEmailByPersonIdHandler(cmd)
}


fun removeEmailByEmailIdEntryPoint(
    ac: AppContext,
    emailId: EmailId
): Either<Exception,String>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveEmailByEmailIdCommand(cmdCtx,emailId)
    return removeEmailByEmailIdHandler(cmd)
}

fun fetchAllEmailEntryPoint(
    ac: AppContext,
): Either<Exception,List<Email>>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchAllEmailCommand(cmdCtx)
    return fetchAllEmailHandler(cmd)
}

fun fetchEmailByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,List<Email>>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchEmailByPersonIdCommand(cmdCtx,personId)
    return fetchEmailByPersonIdHandler(cmd)
}

fun fetchEmailByPersonNameEntryPoint(
    ac: AppContext,
    personName: String,
): Either<Exception,List<Email>>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchEmailByPersonNameCommand(cmdCtx,personName)
    return fetchEmailByPersonNameHandler(cmd)
}