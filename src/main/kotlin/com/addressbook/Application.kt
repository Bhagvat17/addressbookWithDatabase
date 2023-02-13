package com.addressbook


import com.addressbook.models.AppContext
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main(args: Array<String>) : Unit{
    io.ktor.server.netty.EngineMain.main(args)
    val dbConnection = connectToDatabase()
    AppContext(dbConnection)
    resetDatabase()

//    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
//        .start(wait = true)




//    var bhagvat = addPersonEntryPoint(AppContext(dbConnection), AddPersonRequest("Bhagvat","jadeja")).orNull()!!
//    var parth = addPersonEntryPoint( AppContext(dbConnection),AddPersonRequest("Parth","Raval")).orNull()!!
//    var hamza = addPersonEntryPoint(AppContext(dbConnection), AddPersonRequest("Hamza","Malik")).orNull()!!
//
//
//    bhagvat = updatePersonEntryPoint(AppContext(dbConnection),UpdatePersonRequest(bhagvat.personId,"Bhagvatsinh","Jadeja")).orNull()!!
//
//    var bhagvat_address2 = addAddressEntryPoint( AppContext(dbConnection),AddAddressRequest(bhagvat.personId, AddressType.Office,"Baroda")).orNull()!!
//    var bhagvat_phoneNumber1 = addPhoneNumberEntryPoint( AppContext(dbConnection),AddPhoneNumberRequest(bhagvat.personId, PhoneNumberType.Home,"2929329392")).orNull()!!
//    var bhagvat_email1 = addEmailEntryPoint( AppContext(dbConnection),AddEmailRequest(bhagvat.personId,EmailType.Home,"bb@gl.com")).orNull()!!
//
//    var group_friends = addGroupEntryPoint( AppContext(dbConnection),AddGroupRequest(groupName = "Friends")).orNull()!!
//    var group_work = addGroupEntryPoint( AppContext(dbConnection),AddGroupRequest(groupName = "Work")).orNull()!!
//    var group_family = addGroupEntryPoint( AppContext(dbConnection),AddGroupRequest(groupName = "Family")).orNull()!!
//    var group_school = addGroupEntryPoint( AppContext(dbConnection),AddGroupRequest(groupName = "School")).orNull()!!
//
//
//    println(showPersonByPersonIdEntryPoint(AppContext(dbConnection), bhagvat.personId))
//    print(showPersonByPersonNameEntryPoint(AppContext(dbConnection),"Parth"))
//    println(listAllPersonEntryPoint(AppContext(dbConnection)))
//
//    parth = updatePersonEntryPoint(AppContext(dbConnection),UpdatePersonRequest(parth.personId,"Paaarth","Ravel")).orNull()!!
////    removePersonEntryPoint(AppContext(dbConnection),bhagvat.personId)
//    println(showPersonByPersonIdEntryPoint(AppContext(dbConnection), bhagvat.personId))
//    println(listAllPersonEntryPoint(AppContext(dbConnection)))
//
//        bhagvat_address2 = updateAddressEntryPoint(AppContext(dbConnection),UpdateAddressRequest(bhagvat_address2.addressId,bhagvat.personId,AddressType.Office,"Rajkot_updated")).orNull()!!
//    bhagvat_phoneNumber1 = updatePhoneNumberEntryPoint( AppContext(dbConnection),UpdatePhoneNumberRequest(bhagvat_phoneNumber1.phoneNumberId,bhagvat.personId,PhoneNumberType.Home,"29293293u")).orNull()!!
//    bhagvat_email1 = updateEmailEntryPoint( AppContext(dbConnection),UpdateEmailRequest(bhagvat_email1.emailId,bhagvat.personId,EmailType.Home,"bb@gl.com_updated")).orNull()!!
////    removeAddressByAddressIdEntryPoint(AppContext(dbConnection),bhagvat_address2.addressId)
//    connectGroupsWithPersonEntryPoint(AppContext(dbConnection),bhagvat.personId, listOf(group_friends.groupId,group_family.groupId))
//    connectPersonsWithGroupEntryPoint(AppContext(dbConnection), group_school.groupId, listOf(bhagvat.personId,parth.personId))
////    removePersonEntryPoint(AppContext(dbConnection),bhagvat.personId)
////    removeGroupEntryPoint(AppContext(dbConnection),group_school.groupId)
////    removePersonFromGroupEntryPoint(AppContext(dbConnection),group_family.groupId,bhagvat.personId)
//    println(listAllAddressEntryPoint(AppContext(dbConnection)))
//    println(showAddressByPersonIdEntryPoint(AppContext(dbConnection),bhagvat.personId))
//    println(showGroupsOfPersonEntryPoint(AppContext(dbConnection),bhagvat.personId))
//    println(showPersonsOfGroupEntryPoint(AppContext(dbConnection),group_school.groupId))
}


@Suppress("unused")
fun Application.module() {
    configureRouting()
    configureSerialization()
}
