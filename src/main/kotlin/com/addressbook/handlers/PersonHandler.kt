package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.Person
import com.addressbook.commands.*

object PersonHandler{
    fun addPersonHandler(cmd: AddPersonCommand): Either<Exception, Person> {
        return cmd.execute()
    }

    fun updatePersonHandler(cmd: UpdatePersonCommand): Either<Exception, Person> {
        return cmd.execute()
    }

    fun removePersonHandler(cmd: RemovePersonCommand): Either<Exception, Any>{
        return cmd.execute()
    }

    fun listAllPersonHandler(cmd: ListAllPersonCommand): Either<Exception, List<Person>>{
        return cmd.execute()
    }

    fun showPersonByPersonIdHandler(cmd: ShowPersonByPersonIdCommand): Either<Exception, List<Person>>{
        return cmd.execute()
    }

    fun showPersonByPersonNameHandler(cmd: ShowPersonByPersonNameCommand): Either<Exception, List<Person>>{
        return cmd.execute()
    }

}