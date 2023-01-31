package com.addressbook

import com.addressbook.entrypoints.*
import com.addressbook.requests.*


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

//    var bhagvat = AddPersonCommand(AddPersonRequest("Bhagvatsinh","jadeja")).execute()
//    var parth = AddPersonCommand( AddPersonRequest("Parth","Raval")).execute()
//    var hamza = AddPersonCommand( AddPersonRequest("Hamza","Malik")).execute()
//    var bhagvat_address2 = AddAddressCommand( AddAddressRequest(bhagvat.personId, AddressType.Office,"Baroda")).execute()
//    var bhagvat_phoneNumber1 = AddPhoneNumberCommand( AddPhoneNumberRequest(bhagvat.personId, PhoneNumberType.Home,"2929329392")).execute()
//    var bhagvat_email1 = AddEmailCommand( AddEmailRequest(bhagvat.personId,EmailType.Home,"bb@gl.com")).execute()
//    var group_friends = AddGroupCommand( AddGroupRequest(groupName = "Friends")).execute()
//    var group_work = AddGroupCommand( AddGroupRequest(groupName = "Work")).execute()
//    var group_family = AddGroupCommand( AddGroupRequest(groupName = "Family")).execute()
//    var group_school = AddGroupCommand( AddGroupRequest(groupName = "School")).execute()
//    parth = UpdatePersonCommand(UpdatePersonRequest(parth.personId,"Paaarth","Ravel")).execute()
////    RemovePersonCommand(PersonDB,bhagvat.personId).execute()
//    bhagvat_address2 = UpdateAddressCommand(UpdateAddressRequest(bhagvat_address2.addressId,bhagvat.personId,AddressType.Office,"Rajkot_updated")).execute()
//    bhagvat_phoneNumber1 = UpdatePhoneNumberCommand( UpdatePhoneNumberRequest(bhagvat_phoneNumber1.phoneNumberId,bhagvat.personId,PhoneNumberType.Home,"29293293u")).execute()
//    bhagvat_email1 = UpdateEmailCommand( UpdateEmailRequest(bhagvat_email1.emailId,bhagvat.personId,EmailType.Home,"bb@gl.com_updated")).execute()
////    RemoveAddressByAddressIdCommand(bhagvat_address2.addressId).execute()
//    ConnectGroupsWithPerson(bhagvat.personId, listOf(group_friends.groupId,group_family.groupId)).execute()
//    ConnectPersonsWithGroup( group_school.groupId, listOf(bhagvat.personId,parth.personId)).execute()
//    RemovePersonCommand(bhagvat.personId).execute()
//    RemoveGroupCommand(group_school.groupId).execute()
//    RemovePersonFromGroupCommand(group_family.groupId,bhagvat.personId).execute()
//    println(ListAllAddressCommand().execute())
//    println(ShowAddressByPersonIdCommand(bhagvat.personId).execute())
//    println(ShowGroupsOfPersonCommand(bhagvat.personId).execute())
//    println(ShowPersonsOfGroupCommand(group_school.groupId).execute())



    var bhagvat = addPersonEntryPoint(AppContext(dbConnection), AddPersonRequest("Bhagvat","jadeja")).orNull()!!
    var parth = addPersonEntryPoint( AppContext(dbConnection),AddPersonRequest("Parth","Raval")).orNull()!!
    var hamza = addPersonEntryPoint(AppContext(dbConnection), AddPersonRequest("Hamza","Malik")).orNull()!!


    bhagvat = updatePersonEntryPoint(AppContext(dbConnection),UpdatePersonRequest(bhagvat.personId,"Bhagvatsinh","Jadeja")).orNull()!!

    var bhagvat_address2 = addAddressEntryPoint( AppContext(dbConnection),AddAddressRequest(bhagvat.personId, AddressType.Office,"Baroda")).orNull()!!
    var bhagvat_phoneNumber1 = addPhoneNumberEntryPoint( AppContext(dbConnection),AddPhoneNumberRequest(bhagvat.personId, PhoneNumberType.Home,"2929329392")).orNull()!!
    var bhagvat_email1 = addEmailEntryPoint( AppContext(dbConnection),AddEmailRequest(bhagvat.personId,EmailType.Home,"bb@gl.com")).orNull()!!

    var group_friends = addGroupEntryPoint( AppContext(dbConnection),AddGroupRequest(groupName = "Friends")).orNull()!!
    var group_work = addGroupEntryPoint( AppContext(dbConnection),AddGroupRequest(groupName = "Work")).orNull()!!
    var group_family = addGroupEntryPoint( AppContext(dbConnection),AddGroupRequest(groupName = "Family")).orNull()!!
    var group_school = addGroupEntryPoint( AppContext(dbConnection),AddGroupRequest(groupName = "School")).orNull()!!


    println(showPersonByPersonIdEntryPoint(AppContext(dbConnection), bhagvat.personId))
    print(showPersonByPersonNameEntryPoint(AppContext(dbConnection),"Parth"))
    println(listAllPersonEntryPoint(AppContext(dbConnection)))

    parth = updatePersonEntryPoint(AppContext(dbConnection),UpdatePersonRequest(parth.personId,"Paaarth","Ravel")).orNull()!!
//    removePersonEntryPoint(AppContext(dbConnection),bhagvat.personId)
    println(showPersonByPersonIdEntryPoint(AppContext(dbConnection), bhagvat.personId))
    println(listAllPersonEntryPoint(AppContext(dbConnection)))

        bhagvat_address2 = updateAddressEntryPoint(AppContext(dbConnection),UpdateAddressRequest(bhagvat_address2.addressId,bhagvat.personId,AddressType.Office,"Rajkot_updated")).orNull()!!
    bhagvat_phoneNumber1 = updatePhoneNumberEntryPoint( AppContext(dbConnection),UpdatePhoneNumberRequest(bhagvat_phoneNumber1.phoneNumberId,bhagvat.personId,PhoneNumberType.Home,"29293293u")).orNull()!!
    bhagvat_email1 = updateEmailEntryPoint( AppContext(dbConnection),UpdateEmailRequest(bhagvat_email1.emailId,bhagvat.personId,EmailType.Home,"bb@gl.com_updated")).orNull()!!
//    removeAddressByAddressIdEntryPoint(AppContext(dbConnection),bhagvat_address2.addressId)
    connectGroupsWithPersonEntryPoint(AppContext(dbConnection),bhagvat.personId, listOf(group_friends.groupId,group_family.groupId))
    connectPersonsWithGroupEntryPoint(AppContext(dbConnection), group_school.groupId, listOf(bhagvat.personId,parth.personId))
//    removePersonEntryPoint(AppContext(dbConnection),bhagvat.personId)
//    removeGroupEntryPoint(AppContext(dbConnection),group_school.groupId)
//    removePersonFromGroupEntryPoint(AppContext(dbConnection),group_family.groupId,bhagvat.personId)
    println(listAllAddressEntryPoint(AppContext(dbConnection)))
    println(showAddressByPersonIdEntryPoint(AppContext(dbConnection),bhagvat.personId))
    println(showGroupsOfPersonEntryPoint(AppContext(dbConnection),bhagvat.personId))
    println(showPersonsOfGroupEntryPoint(AppContext(dbConnection),group_school.groupId))
}

