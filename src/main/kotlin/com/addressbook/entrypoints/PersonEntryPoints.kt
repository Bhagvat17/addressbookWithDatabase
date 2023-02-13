package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.models.AppContext
import com.addressbook.models.CommandContext
import com.addressbook.models.Person
import com.addressbook.models.PersonId
import com.addressbook.commands.*
import com.addressbook.handlers.PersonHandler.addPersonHandler
import com.addressbook.handlers.PersonHandler.fetchAllPersonHandler
import com.addressbook.handlers.PersonHandler.fetchPersonByPersonIdHandler
import com.addressbook.handlers.PersonHandler.fetchPersonByPersonNameHandler
import com.addressbook.handlers.PersonHandler.removePersonHandler
import com.addressbook.handlers.PersonHandler.updatePersonHandler
import com.addressbook.requests.AddPersonRequest
import com.addressbook.requests.UpdatePersonRequest

fun addPersonEntryPoint(
    ac: AppContext,
    req: AddPersonRequest
): Either<Exception, Person> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddPersonCommand(cmdCtx,req)
    return addPersonHandler(cmd)
}

fun updatePersonEntryPoint(
    ac: AppContext,
    req: UpdatePersonRequest
): Either<Exception, Person>{
    val cmdCtx = CommandContext(ac.db)
    val cmd = UpdatePersonCommand(cmdCtx, req)
    return updatePersonHandler(cmd)
}

fun removePersonEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception,String>{
    val cmdCtx = CommandContext(ac.db)
    val cmd = RemovePersonCommand(cmdCtx, personId)
    return removePersonHandler(cmd)
}

fun fetchAllPersonEntryPoint(
    ac: AppContext,
): Either<Exception, List<Person>>{
    val cmdCtx = CommandContext(ac.db)
    val cmd = fetchAllPersonCommand(cmdCtx)
    return fetchAllPersonHandler(cmd)
}

fun fetchPersonByPersonIdEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception, Person>{
    val cmdCtx = CommandContext(ac.db)
    val cmd = fetchPersonByPersonIdCommand(cmdCtx,personId)
    return fetchPersonByPersonIdHandler(cmd)
}

fun fetchPersonByPersonNameEntryPoint(
    ac: AppContext,
    personName: String
): Either<Exception, Person> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = fetchPersonByPersonNameCommand(cmdCtx,personName)
    return fetchPersonByPersonNameHandler(cmd)
}