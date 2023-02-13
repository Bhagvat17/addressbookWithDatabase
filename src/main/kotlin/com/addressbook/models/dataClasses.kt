package com.addressbook.models

import com.addressbook.requests.*
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.UUID

typealias PersonId = UUID

@JsonSerialize
data class Contact(
    val firstName: String,
    val lastName: String,
    val addressList: List<AddAddressApi>,
    val phoneList: List<AddPhoneNumberApi>,
    val emailList: List<AddEmailApi>,
    val groupList: List<AddGroupRequest>
)


data class AddAddressApi(
    val addressType: String,
    val addressLine: String,
)
data class AddPhoneNumberApi(
    val phoneNumberType: String,
    val phone: String,
)
data class AddEmailApi(
    val emailType: String,
    val emailAddress: String,
)

data class Person(
    val personId: PersonId,
    val firstName: String,
    val lastName: String
)

val personStorage = mutableListOf<Person>()

typealias AddressId = UUID

data class Address(
    val addressId: AddressId,
    val personId: PersonId,
    val addressType: String,
    val addressLine: String,
)

typealias PhoneNumberId = UUID
data class PhoneNumber(
    val phoneNumberId: PhoneNumberId,
    val personId: PersonId,
    val phoneNumberType: String,
    val phone: String,
)

typealias EmailId = UUID
data class Email(
    val emailId: EmailId,
    val personId: PersonId,
    val emailType: String,
    val emailAddress: String,
)

typealias GroupId = UUID

data class Group(
    val groupId: GroupId,
    val groupName: String,
)