package com.addressbook

import AddAddressCommand
import AddPersonCommand
import PersonDB
import UpdatePersonCommand
import com.addressbook.storages.AddressDB
import com.addressbook.tables.Addresses
import com.addressbook.tables.Emails
import com.addressbook.tables.Persons
import com.addressbook.tables.PhoneNumbers
import com.example.addressbook.requests.AddAddressRequest
import com.example.addressbook.requests.AddPersonRequest
import com.example.addressbook.requests.AddressType
import com.example.addressbook.requests.UpdatePersonRequest
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    val url = "jdbc:mysql://localhost:3306/addressBookDB"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "bhagvat"
    val password = "Bhagvat17@"

    Database.connect(url, driver, username, password)

    transaction {
        SchemaUtils.drop(Persons, PhoneNumbers, Emails, Addresses)
        SchemaUtils.create(Persons, PhoneNumbers, Emails, Addresses)
    }

    val person1 = AddPersonRequest("Bhagvatsinh","jadeja")
    val bhagvat = AddPersonCommand(PersonDB, person1).execute()
    val person2 = AddPersonRequest("Parth","Raval")
    val parth = AddPersonCommand(PersonDB, person2).execute()
    val person3 = AddPersonRequest("Hamza","Malik")
    val hamza = AddPersonCommand(PersonDB, person3).execute()
    AddAddressCommand(AddressDB, AddAddressRequest(bhagvat.personId,AddressType.Office,"Gondal")).execute()
    UpdatePersonCommand(PersonDB,UpdatePersonRequest(parth.personId,"Paaarth","Ravel")).execute()

}

