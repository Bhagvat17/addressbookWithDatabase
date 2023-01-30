package com.addressbook.commands

import com.addressbook.storages.PersonDB
import com.example.addressbookdb.Person
import com.example.addressbookdb.PersonId
import com.example.addressbookdb.requests.AddPersonRequest
import com.example.addressbookdb.requests.UpdatePersonRequest
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
    private val storage: PersonDB,
    private val request: AddPersonRequest
): Command {
    override fun execute(): Person {
        val person = request.toPerson()


        val personDetail = storage.addPerson(person)

        return Person(
            personId = personDetail.personId,
            firstName = personDetail.firstName,
            lastName = personDetail.lastName,
        )
    }
}


class UpdatePersonCommand(
    private val storage: PersonDB,
    private val request: UpdatePersonRequest) : Command {
    override fun execute(): Person {
        val person = request.toPerson()
        val personDetail = storage.updatePerson(person)
        return Person(
            personId = personDetail.personId,
            firstName = personDetail.firstName,
            lastName = personDetail.lastName,
        )
    }
}



class RemovePersonCommand(
    private val storage: PersonDB,
    private val personId: PersonId,
) : Command {
    override fun execute(): Any {
        storage.removePerson(personId)
        return " contact deleted"
    }
}

class ListAllPersonCommand(
    private val storage: PersonDB,
): Command{
    override fun execute(): List<Person> {
        return storage.listAllPerson()
    }
}