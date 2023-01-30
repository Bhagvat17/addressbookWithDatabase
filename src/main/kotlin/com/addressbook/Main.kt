package com.addressbook

import AppContext
import com.addressbook.commands.*
import com.addressbook.requests.AddGroupRequest
import com.addressbook.storages.*
import com.example.addressbookdb.requests.*

fun main(args: Array<String>) {
//    val url = "jdbc:mysql://localhost:3306/addressBookDB"
//    val driver = "com.mysql.cj.jdbc.Driver"
//    val username = "bhagvat"
//    val password = "Bhagvat17@"
//
//    Database.connect(url, driver, username, password)
//
//
//    transaction {
//        SchemaUtils.drop(GroupPersonsAssociation, PhoneNumbers, Emails, Addresses,GroupStorage,Persons)
//        SchemaUtils.create(Persons, PhoneNumbers, Emails, Addresses, GroupStorage,GroupPersonsAssociation)
//    }

    val dbConnection = connectToDatabase()
    AppContext(dbConnection)
    resetDatabase()

    var bhagvat = AddPersonCommand(PersonDB, AddPersonRequest("Bhagvatsinh","jadeja")).execute()
    var parth = AddPersonCommand(PersonDB, AddPersonRequest("Parth","Raval")).execute()
    var hamza = AddPersonCommand(PersonDB, AddPersonRequest("Hamza","Malik")).execute()
    var bhagvat_address2 = AddAddressCommand(AddressDB, AddAddressRequest(bhagvat.personId, AddressType.Office,"Baroda")).execute()
    var bhagvat_phoneNumber1 = AddPhoneNumberCommand(PhoneNumberDB, AddPhoneNumberRequest(bhagvat.personId, PhoneNumberType.Home,"2929329392")).execute()
    var bhagvat_email1 = AddEmailCommand(EmailDB, AddEmailRequest(bhagvat.personId,EmailType.Home,"bb@gl.com")).execute()
    var group_friends = AddGroupCommand(GroupDB, AddGroupRequest(groupName = "Friends")).execute()
    var group_work = AddGroupCommand(GroupDB, AddGroupRequest(groupName = "Work")).execute()
    var group_family = AddGroupCommand(GroupDB, AddGroupRequest(groupName = "Family")).execute()
    var group_school = AddGroupCommand(GroupDB, AddGroupRequest(groupName = "School")).execute()
    parth = UpdatePersonCommand(PersonDB,UpdatePersonRequest(parth.personId,"Paaarth","Ravel")).execute()
//    RemovePersonCommand(PersonDB,bhagvat.personId).execute()
    bhagvat_address2 = UpdateAddressCommand(AddressDB,UpdateAddressRequest(bhagvat_address2.addressId,bhagvat.personId,AddressType.Office,"Rajkot_updated")).execute()
    bhagvat_phoneNumber1 = UpdatePhoneNumberCommand(PhoneNumberDB, UpdatePhoneNumberRequest(bhagvat_phoneNumber1.phoneNumberId,bhagvat.personId,PhoneNumberType.Home,"29293293u")).execute()
    bhagvat_email1 = UpdateEmailCommand(EmailDB, UpdateEmailRequest(bhagvat_email1.emailId,bhagvat.personId,EmailType.Home,"bb@gl.com_updated")).execute()
//    RemoveAddressByAddressIdCommand(AddressDB,bhagvat_address2.addressId).execute()
    ConnectGroupsWithPerson(bhagvat.personId, listOf(group_friends.groupId,group_family.groupId)).execute()
    ConnectPersonsWithGroup( group_school.groupId, listOf(bhagvat.personId,parth.personId)).execute()
//    RemovePersonCommand(PersonDB,bhagvat.personId).execute()
//    RemoveGroupCommand(group_school.groupId).execute()
    RemovePersonFromGroupCommand(group_family.groupId,bhagvat.personId).execute()
}

