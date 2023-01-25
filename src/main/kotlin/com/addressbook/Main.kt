package com.addressbook

import AddAddressCommand
import AddPersonCommand
import AddPhoneNumberCommand
import PersonDB
import UpdatePersonCommand
import com.addressbook.storages.AddressDB
import com.addressbook.storages.PhoneNumberDB
import com.addressbook.tables.Addresses
import com.addressbook.tables.Emails
import com.addressbook.tables.Persons
import com.addressbook.tables.PhoneNumbers
import com.example.addressbook.requests.*
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

    val bhagvat = AddPersonCommand(PersonDB, AddPersonRequest("Bhagvatsinh","jadeja")).execute()
    val parth = AddPersonCommand(PersonDB, AddPersonRequest("Parth","Raval")).execute()
    val hamza = AddPersonCommand(PersonDB, AddPersonRequest("Hamza","Malik")).execute()
    AddAddressCommand(AddressDB, AddAddressRequest(bhagvat.personId,AddressType.Office,"Gondal")).execute()
    AddPhoneNumberCommand(PhoneNumberDB, AddPhoneNumberRequest(bhagvat.personId,PhoneNumberType.Home,"2929329392")).execute()
    UpdatePersonCommand(PersonDB,UpdatePersonRequest(parth.personId,"Paaarth","Ravel")).execute()
}

