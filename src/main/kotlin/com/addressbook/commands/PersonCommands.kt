package com.addressbook.commands

import arrow.core.Either
import com.addressbook.models.CommandContext
import com.addressbook.models.Person
import com.addressbook.models.PersonId
import com.addressbook.requests.AddPersonRequest
import com.addressbook.requests.UpdatePersonRequest
import com.addressbook.repos.PersonRepo
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
    override fun execute(): Either<Exception, Person> = PersonRepo.addPerson(request.toPerson())
}


class UpdatePersonCommand(
    val cmdCtx: CommandContext,
    private val request: UpdatePersonRequest
) : Command {
    override fun execute(): Either<Exception, Person> = PersonRepo.updatePerson(request.toPerson())
}



class RemovePersonCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
) : Command {
    override fun execute(): Either<Exception,String> = PersonRepo.removePerson(personId)
}

class fetchAllPersonCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception, List<Person>> {
        return PersonRepo.fetchAllPerson()
    }
}

class fetchPersonByPersonIdCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, Person> {
        return PersonRepo.fetchPersonByPersonId(personId)
    }
}

class fetchPersonByPersonNameCommand(
    val cmdCtx: CommandContext,
    private val personName: String
): Command{
    override fun execute(): Either<Exception,Person> {
        return PersonRepo.fetchPersonByPersonName(personName)
    }
}