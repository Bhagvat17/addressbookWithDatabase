package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.models.Person
import com.addressbook.commands.*

object PersonHandler{
    fun addPersonHandler(cmd: AddPersonCommand): Either<Exception, Person> {
        return cmd.execute()
    }

    fun updatePersonHandler(cmd: UpdatePersonCommand): Either<Exception, Person> {
        return cmd.execute()
    }

    fun removePersonHandler(cmd: RemovePersonCommand): Either<Exception, String>{
        return cmd.execute()
    }

    fun fetchAllPersonHandler(cmd: fetchAllPersonCommand): Either<Exception, List<Person>>{
        return cmd.execute()
    }

    fun fetchPersonByPersonIdHandler(cmd: fetchPersonByPersonIdCommand): Either<Exception,Person>{
        return cmd.execute()
    }

    fun fetchPersonByPersonNameHandler(cmd: fetchPersonByPersonNameCommand): Either<Exception, Person>{
        return cmd.execute()
    }

}