package com.example.addressbookdb

import com.example.addressbookdb.requests.AddressType
import com.example.addressbookdb.requests.EmailType
import com.example.addressbookdb.requests.PhoneNumberType
import java.util.UUID

typealias PersonId = UUID

data class Person(
    val personId: PersonId,
    val firstName: String,
    val lastName: String
)


typealias AddressId = UUID

data class Address(
    val addressId: AddressId,
    val personId: PersonId,
    val addressType: AddressType,
    val addressLine: String,
)

typealias PhoneNumberId = UUID
data class PhoneNumber(
    val phoneNumberId: PhoneNumberId,
    val personId: PersonId,
    val phoneNumberType: PhoneNumberType,
    val phone: String,
)

typealias EmailId = UUID
data class Email(
    val emailId: EmailId,
    val personId: PersonId,
    val emailType: EmailType,
    val emailAddress: String,
)

typealias GroupId = UUID

data class Group(
    val groupId: GroupId,
    val groupName: String,
)