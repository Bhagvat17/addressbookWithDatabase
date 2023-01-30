package com.addressbook

import AppContext
import com.addressbook.commands.AddPersonCommand
import com.addressbook.storages.PersonDB
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PersonTest: AppTest(){
    @Test
    fun `create person`(): Unit {

        val dbConnection = connectToDatabase()
        AppContext(dbConnection)
        resetDatabase()

        val personResponse = AddPersonCommand(PersonDB, getPersonCreateRequest()).execute()

        Assertions.assertEquals("BhagvatSinh", personResponse.firstName)
        Assertions.assertEquals("Jadeja", personResponse.lastName)

    }

}