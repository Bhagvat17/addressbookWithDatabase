package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.*
import com.addressbook.commands.*
import com.addressbook.requests.AddEmailRequest
import com.addressbook.requests.UpdateEmailRequest

fun addEmailEntryPoint(
    ac: AppContext,
    req: AddEmailRequest
): Either<Exception, Email> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddEmailCommand(cmdCtx,req)
    return cmd.execute()
}

fun updateEmailEntryPoint(
    ac: AppContext,
    req: UpdateEmailRequest
): Either<Exception, Email> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = UpdateEmailCommand(cmdCtx,req)
    return cmd.execute()
}


fun removeEmailByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,String>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveEmailByPersonIdCommand(cmdCtx,personId)
    return cmd.execute()
}


fun removeEmailByEmailIdEntryPoint(
    ac: AppContext,
    emailId: EmailId
): Either<Exception,String>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveEmailByEmailIdCommand(cmdCtx,emailId)
    return cmd.execute()
}

fun listAllEmailEntryPoint(
    ac: AppContext,
): Either<Exception,Any>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = ListAllEmailCommand(cmdCtx)
    return cmd.execute()
}

fun showEmailByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,Any>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = ShowEmailByPersonIdCommand(cmdCtx,personId)
    return cmd.execute()
}

fun showEmailByPersonNameEntryPoint(
    ac: AppContext,
    personName: String,
): Either<Exception,Any>{
    val cmdCtx= CommandContext(ac.db)
    val cmd = ShowEmailByPersonNameCommand(cmdCtx,personName)
    return cmd.execute()
}