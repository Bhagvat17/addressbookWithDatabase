package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.AppContext
import com.addressbook.CommandContext
import com.addressbook.Person
import com.addressbook.PersonId
import com.addressbook.commands.*
import com.addressbook.requests.AddPersonRequest
import com.addressbook.requests.UpdatePersonRequest

fun addPersonEntryPoint(
    ac: AppContext,
    req: AddPersonRequest
): Either<Exception, Person> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddPersonCommand(cmdCtx,req)
    return cmd.execute()
}

fun updatePersonEntryPoint(
    ac: AppContext,
    req: UpdatePersonRequest
): Either<Exception,Person>{
    val cmdCtx = CommandContext(ac.db)
    val cmd = UpdatePersonCommand(cmdCtx, req)
    return cmd.execute()
}

fun removePersonEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,Any>{
    val cmdCtx = CommandContext(ac.db)
    val cmd = RemovePersonCommand(cmdCtx, personId)
    return cmd.execute()
}

fun listAllPersonEntryPoint(
    ac: AppContext,
): Either<Exception, List<Person>>{
    val cmdCtx = CommandContext(ac.db)
    val cmd = ListAllPersonCommand(cmdCtx)
    return cmd.execute()
}

fun showPersonByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception, List<Person>>{
    val cmdCtx = CommandContext(ac.db)
    val cmd = ShowPersonByPersonIdCommand(cmdCtx,personId)
    return cmd.execute()
}

fun showPersonByPersonNameEntryPoint(
    ac: AppContext,
    personName: String
): Either<Exception, List<Person>> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = ShowPersonByPersonNameCommand(cmdCtx,personName)
    return cmd.execute()
}