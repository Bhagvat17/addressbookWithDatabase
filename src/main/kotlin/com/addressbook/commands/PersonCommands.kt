package com.addressbook.commands

import arrow.core.Either
import com.addressbook.CommandContext
import com.addressbook.Person
import com.addressbook.PersonId
import com.addressbook.requests.AddPersonRequest
import com.addressbook.requests.UpdatePersonRequest
import com.addressbook.storages.PersonDB
import java.util.*


fun AddPersonRequest.toPerson() =
    Person(
        personId = UUID.randomUUID(),
        firstName = this@toPerson.firstName,
        lastName = this@toPerson.lastName
    )


fun UpdatePersonRequest.toPerson() =
    Person(
        personId = this@toPerson.personId,
        firstName = this@toPerson.firstName,
        lastName = this@toPerson.lastName
    )




class AddPersonCommand(
    val cmdCtx: CommandContext,
    private val request: AddPersonRequest
): Command {
    override fun execute(): Either<Exception, Person> = PersonDB.addPerson(request.toPerson())
}


class UpdatePersonCommand(
    val cmdCtx: CommandContext,
    private val request: UpdatePersonRequest
) : Command {
    override fun execute(): Either<Exception,Person> = PersonDB.updatePerson(request.toPerson())
}



class RemovePersonCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
) : Command {
    override fun execute(): Either<Exception,Any> = PersonDB.removePerson(personId)
}

class ListAllPersonCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception, List<Person>> {
        return PersonDB.listAllPerson()
    }
}

class ShowPersonByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, List<Person>> {
        return PersonDB.showPersonByPersonId(personId)
    }
}

class ShowPersonByPersonNameCommand(
    val cmdCtx: CommandContext,
    private val personName: String
): Command{
    override fun execute(): Either<Exception, List<Person>> {
        return PersonDB.showPersonByPersonName(personName)
    }
}