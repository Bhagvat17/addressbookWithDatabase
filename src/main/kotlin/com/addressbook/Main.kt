package com.addressbook

import AddGroupCommand
import com.addressbook.commands.*
import com.addressbook.requests.AddGroupRequest
import com.addressbook.storages.*
import com.addressbook.tables.*
import com.example.addressbookdb.requests.*
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
        SchemaUtils.drop(Persons, PhoneNumbers, Emails, Addresses, Groups)
        SchemaUtils.create(Persons, PhoneNumbers, Emails, Addresses, Groups)
    }

    var bhagvat = AddPersonCommand(PersonDB, AddPersonRequest("Bhagvatsinh","jadeja")).execute()
    var parth = AddPersonCommand(PersonDB, AddPersonRequest("Parth","Raval")).execute()
    var hamza = AddPersonCommand(PersonDB, AddPersonRequest("Hamza","Malik")).execute()
    var bhagvat_address2 = AddAddressCommand(AddressDB, AddAddressRequest(bhagvat.personId,AddressType.Office,"Baroda")).execute()
    var bhagvat_phoneNumber1 = AddPhoneNumberCommand(PhoneNumberDB, AddPhoneNumberRequest(bhagvat.personId,PhoneNumberType.Home,"2929329392")).execute()
    var bhagvat_email1 = AddEmailCommand(EmailDB, AddEmailRequest(bhagvat.personId,EmailType.Home,"bb@gl.com")).execute()
    AddGroupCommand(GroupDB, AddGroupRequest(bhagvat.personId, groupName = "Friends")).execute()
    parth = UpdatePersonCommand(PersonDB,UpdatePersonRequest(parth.personId,"Paaarth","Ravel")).execute()
//    RemovePersonCommand(PersonDB,bhagvat.personId).execute()
    bhagvat_address2 = UpdateAddressCommand(AddressDB,UpdateAddressRequest(bhagvat_address2.addressId,bhagvat.personId,AddressType.Office,"Rajkot_updated")).execute()
    bhagvat_phoneNumber1 = UpdatePhoneNumberCommand(PhoneNumberDB, UpdatePhoneNumberRequest(bhagvat_phoneNumber1.phoneNumberId,bhagvat.personId,PhoneNumberType.Home,"29293293u")).execute()
    bhagvat_email1 = UpdateEmailCommand(EmailDB, UpdateEmailRequest(bhagvat_email1.emailId,bhagvat.personId,EmailType.Home,"bb@gl.com_updated")).execute()

}

