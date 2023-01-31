package com.addressbook

import com.addressbook.commands.*
import com.addressbook.entrypoints.*
import com.addressbook.storages.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PersonTest: AppTest(){
    @Test
    fun `create person`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
    }

    @Test
    fun `update person`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
        var parth = addPersonEntryPoint(appCtx,getPersonCreateRequest2()).orNull()!!
        val hamza = addPersonEntryPoint(appCtx, getPersonCreateRequest3()).orNull()!!
        Assertions.assertEquals("Parth",parth.firstName)
        parth = updatePersonEntryPoint(appCtx, getPersonUpdateRequest2(parth.personId)).orNull()!!
        Assertions.assertEquals("Paaaarth", parth.firstName)
    }

    @Test
    fun `remove person`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
        var parth = addPersonEntryPoint(appCtx, getPersonCreateRequest2()).orNull()!!
        val hamza = addPersonEntryPoint(appCtx, getPersonCreateRequest3()).orNull()!!
        Assertions.assertEquals("Hamza", hamza.firstName)
        removePersonEntryPoint(appCtx,hamza.personId)
        println(PersonDB.listAllPerson())
    }


    @Test
    fun `add address`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        val bhagvat_address = addAddressEntryPoint(appCtx, getAddressCreateRequest(bhagvat.personId)).orNull()!!
        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
    }

    @Test
    fun `update address`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        var bhagvat_address = addAddressEntryPoint(appCtx, getAddressCreateRequest(bhagvat.personId)).orNull()!!
        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
        bhagvat_address = updateAddressEntryPoint(appCtx, getAddressUpdateRequest(bhagvat_address.addressId,bhagvat.personId)).orNull()!!
        println(bhagvat_address)
    }

    @Test
    fun `remove address by addressId`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
        val hamza = addPersonEntryPoint(appCtx, getPersonCreateRequest3()).orNull()!!
        val bhagvat_address = addAddressEntryPoint(appCtx, getAddressCreateRequest(bhagvat.personId)).orNull()!!
        val hamza_address = addAddressEntryPoint(appCtx, getAddressCreateRequest2(hamza.personId)).orNull()!!

        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
        Assertions.assertEquals("Surat", hamza_address.addressLine)
        println(listAllAddressEntryPoint(appCtx))
        removeAddressByAddressIdEntryPoint(appCtx, bhagvat_address.addressId)
        println(listAllAddressEntryPoint(appCtx))
    }


    @Test
    fun `remove address by personId`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)
        val hamza = addPersonEntryPoint(appCtx, getPersonCreateRequest3()).orNull()!!
        val bhagvat_address = addAddressEntryPoint(appCtx, getAddressCreateRequest(bhagvat.personId)).orNull()!!
        val hamza_address = addAddressEntryPoint(appCtx, getAddressCreateRequest2(hamza.personId)).orNull()!!

        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
        Assertions.assertEquals("Surat", hamza_address.addressLine)
        println(listAllAddressEntryPoint(appCtx))
        removeAddressByPersonIdEntryPoint(appCtx, bhagvat.personId)
        println(listAllAddressEntryPoint(appCtx))
    }


    @Test
    fun `show address`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        val hamza = addPersonEntryPoint(appCtx, getPersonCreateRequest3()).orNull()!!
        val bhagvat_address = addAddressEntryPoint(appCtx, getAddressCreateRequest(bhagvat.personId)).orNull()!!
        val hamza_address = addAddressEntryPoint(appCtx, getAddressCreateRequest2(hamza.personId)).orNull()!!

        Assertions.assertEquals("Baroda", bhagvat_address.addressLine)
        Assertions.assertEquals("Surat", hamza_address.addressLine)

//        println(showAddressByPersonIdEntryPoint(appCtx,bhagvat.personId))
        println(listAllAddressEntryPoint(appCtx))
    }

    @Test
    fun `add phoneNumber`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        val bhagvat_phoneNumber = addPhoneNumberEntryPoint(appCtx, getPhoneNumberCreateRequest(bhagvat.personId)).orNull()!!
        Assertions.assertEquals("23423", bhagvat_phoneNumber.phone)
    }

    @Test
    fun `add email`(): Unit {
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        Assertions.assertEquals("BhagvatSinh", bhagvat.firstName)
        Assertions.assertEquals("Jadeja", bhagvat.lastName)

        val bhagvat_email = addEmailEntryPoint(appCtx, getEmailCreateRequest(bhagvat.personId)).orNull()!!
        Assertions.assertEquals("abc@email.com", bhagvat_email.emailAddress)
    }

    @Test
    fun `add group`(): Unit {

        val group_friend = addGroupEntryPoint(appCtx, getGroupCreateRequest1()).orNull()!!
        Assertions.assertEquals("Friends", group_friend.groupName)

        val group_family = addGroupEntryPoint(appCtx, getGroupCreateRequest2()).orNull()!!
        Assertions.assertEquals("Family", group_family.groupName)

        val group_home = addGroupEntryPoint(appCtx, getGroupCreateRequest3()).orNull()!!
        Assertions.assertEquals("Home", group_home.groupName)

        val group_school = addGroupEntryPoint(appCtx, getGroupCreateRequest4()).orNull()!!
        Assertions.assertEquals("School", group_school.groupName)

    }

    @Test
    fun `connect group with person`(): Unit{
        val bhagvat = addPersonEntryPoint(appCtx, getPersonCreateRequest1()).orNull()!!
        var parth = addPersonEntryPoint(appCtx, getPersonCreateRequest2()).orNull()!!
        val hamza = addPersonEntryPoint(appCtx, getPersonCreateRequest3()).orNull()!!

        val group_friend = addGroupEntryPoint(appCtx, getGroupCreateRequest1()).orNull()!!
        Assertions.assertEquals("Friends", group_friend.groupName)

        val group_family = addGroupEntryPoint(appCtx, getGroupCreateRequest2()).orNull()!!
        Assertions.assertEquals("Family", group_family.groupName)

        val group_home = addGroupEntryPoint(appCtx, getGroupCreateRequest3()).orNull()!!
        Assertions.assertEquals("Home", group_home.groupName)

        val group_school = addGroupEntryPoint(appCtx, getGroupCreateRequest4()).orNull()!!
        Assertions.assertEquals("School", group_school.groupName)

        connectGroupsWithPersonEntryPoint(appCtx,bhagvat.personId, listOf(group_friend.groupId,group_family.groupId))
        connectPersonsWithGroupEntryPoint(appCtx,group_school.groupId, listOf(bhagvat.personId,parth.personId))
    }

}

