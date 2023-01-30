package com.addressbook

import AppContext
import com.addressbook.commands.*
import com.addressbook.storages.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PersonTest: AppTest(){
    @Test
    fun `create person`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
    }

    @Test
    fun `update person`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
        var parth = AddPersonCommand(PersonDB, getPersonCreateRequest2()).execute()
        val hamza = AddPersonCommand(PersonDB, getPersonCreateRequest3()).execute()
        Assertions.assertEquals("Parth",parth.firstName)
        parth = UpdatePersonCommand(PersonDB, getPersonUpdateRequest2(parth.personId)).execute()
        Assertions.assertEquals("Paaaarth", parth.firstName)
    }

    @Test
    fun `remove person`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
        var parth = AddPersonCommand(PersonDB, getPersonCreateRequest2()).execute()
        val hamza = AddPersonCommand(PersonDB, getPersonCreateRequest3()).execute()
        Assertions.assertEquals("Hamza", hamza.firstName)
        RemovePersonCommand(PersonDB,hamza.personId).execute()
        println(PersonDB.listAllPerson())
    }


    @Test
    fun `add address`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        val bhagvat_address = AddAddressCommand(AddressDB, getAddressCreateRequest(bhagvat.personId)).execute()
        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
    }

    @Test
    fun `update address`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        var bhagvat_address = AddAddressCommand(AddressDB, getAddressCreateRequest(bhagvat.personId)).execute()
        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
        bhagvat_address = UpdateAddressCommand(AddressDB, getAddressUpdateRequest(bhagvat_address.addressId,bhagvat.personId)).execute()
        println(bhagvat_address)
    }

    @Test
    fun `remove address by addressId`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
        val hamza = AddPersonCommand(PersonDB, getPersonCreateRequest3()).execute()
        val bhagvat_address = AddAddressCommand(AddressDB, getAddressCreateRequest(bhagvat.personId)).execute()
        val hamza_address = AddAddressCommand(AddressDB, getAddressCreateRequest2(hamza.personId)).execute()

        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
        Assertions.assertEquals("Surat", hamza_address.addressLine)
        println(ListAllAddressCommand(AddressDB).execute())
        RemoveAddressByAddressIdCommand(AddressDB, bhagvat_address.addressId).execute()
        println(ListAllAddressCommand(AddressDB).execute())
    }


    @Test
    fun `remove address by personId`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
        val hamza = AddPersonCommand(PersonDB, getPersonCreateRequest3()).execute()
        val bhagvat_address = AddAddressCommand(AddressDB, getAddressCreateRequest(bhagvat.personId)).execute()
        val hamza_address = AddAddressCommand(AddressDB, getAddressCreateRequest2(hamza.personId)).execute()

        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
        Assertions.assertEquals("Surat", hamza_address.addressLine)
        println(ListAllAddressCommand(AddressDB).execute())
        RemoveAddressByPersonIdCommand(AddressDB, bhagvat.personId).execute()
        println(ListAllAddressCommand(AddressDB).execute())
    }


    @Test
    fun `show address`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        val hamza = AddPersonCommand(PersonDB, getPersonCreateRequest3()).execute()
        val bhagvat_address = AddAddressCommand(AddressDB, getAddressCreateRequest(bhagvat.personId)).execute()
        val hamza_address = AddAddressCommand(AddressDB, getAddressCreateRequest2(hamza.personId)).execute()

        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
        Assertions.assertEquals("Surat", hamza_address.addressLine)

        println(ShowAddressByPersonIdCommand(AddressDB,bhagvat.personId).execute())
    }

    @Test
    fun `add phoneNumber`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        val bhagvat_phoneNumber = AddPhoneNumberCommand(PhoneNumberDB, getPhoneNumberCreateRequest(bhagvat.personId)).execute()
        Assertions.assertEquals("23423", bhagvat_phoneNumber.phone)
    }

    @Test
    fun `add email`(): Unit {
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        val bhagvat_email = AddEmailCommand(EmailDB, getEmailCreateRequest(bhagvat.personId)).execute()
        Assertions.assertEquals("abc@email.com", bhagvat_email.emailAddress)
    }

    @Test
    fun `add group`(): Unit {

        val group_friend = AddGroupCommand(GroupDB, getGroupCreateRequest1()).execute()
        Assertions.assertEquals("Friends", group_friend.groupName)

        val group_family = AddGroupCommand(GroupDB, getGroupCreateRequest2()).execute()
        Assertions.assertEquals("Family", group_family.groupName)

        val group_home = AddGroupCommand(GroupDB, getGroupCreateRequest3()).execute()
        Assertions.assertEquals("Home", group_home.groupName)

        val group_school = AddGroupCommand(GroupDB, getGroupCreateRequest4()).execute()
        Assertions.assertEquals("School", group_school.groupName)

    }

    @Test
    fun `connect group with person`(): Unit{
        val bhagvat = AddPersonCommand(PersonDB, getPersonCreateRequest1()).execute()
        var parth = AddPersonCommand(PersonDB, getPersonCreateRequest2()).execute()
        val hamza = AddPersonCommand(PersonDB, getPersonCreateRequest3()).execute()


        val group_friend = AddGroupCommand(GroupDB, getGroupCreateRequest1()).execute()
        Assertions.assertEquals("Friends", group_friend.groupName)

        val group_family = AddGroupCommand(GroupDB, getGroupCreateRequest2()).execute()
        Assertions.assertEquals("Family", group_family.groupName)

        val group_home = AddGroupCommand(GroupDB, getGroupCreateRequest3()).execute()
        Assertions.assertEquals("Home", group_home.groupName)

        val group_school = AddGroupCommand(GroupDB, getGroupCreateRequest4()).execute()
        Assertions.assertEquals("School", group_school.groupName)

        ConnectGroupsWithPerson(bhagvat.personId, listOf(group_friend.groupId,group_family.groupId)).execute()
        ConnectPersonsWithGroup(group_school.groupId, listOf(bhagvat.personId,parth.personId)).execute()
    }

}

