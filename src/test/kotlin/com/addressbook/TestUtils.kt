package com.addressbook

import com.addressbook.requests.AddGroupRequest
import com.example.addressbookdb.AddressId
import com.example.addressbookdb.EmailId
import com.example.addressbookdb.PersonId
import com.example.addressbookdb.PhoneNumberId
import com.example.addressbookdb.requests.*

fun getPersonCreateRequest1(
    firstName: String = "BhagvatSinh",
    lastName: String = "Jadeja",
) =
    AddPersonRequest(
        firstName = firstName,
        lastName = lastName,
    )

fun getPersonCreateRequest2(
    firstName: String = "Parth",
    lastName: String = "Raval",
) =
    AddPersonRequest(
        firstName = firstName,
        lastName = lastName,
    )
fun getPersonCreateRequest3(
    firstName: String = "Hamza",
    lastName: String = "Malik",
) =
    AddPersonRequest(
        firstName = firstName,
        lastName = lastName,
    )


fun getPersonUpdateRequest2(
    personId: PersonId,
    firstName: String = "Paaaarth",
    lastName: String = "Raval",
    )
 =
    UpdatePersonRequest(
        personId= personId,
        firstName = firstName,
        lastName = lastName,
    )

fun getAddressCreateRequest(
    personId: PersonId,
    addressType: AddressType = AddressType.Home,
    addressLine: String = "Baroda",
) =
    AddAddressRequest(
        personId = personId,
        addressType = addressType,
        addressLine = addressLine
    )

fun getAddressCreateRequest2(
    personId: PersonId,
    addressType: AddressType = AddressType.Home,
    addressLine: String = "Surat",
) =
    AddAddressRequest(
        personId = personId,
        addressType = addressType,
        addressLine = addressLine
    )

fun getAddressUpdateRequest(
    addressId: AddressId,
    personId: PersonId,
    addressType: AddressType = AddressType.Home,
    addressLine: String = "Baroda_Updated"
)
        =
    UpdateAddressRequest(
        addressId = addressId,
        personId= personId,
        addressType = addressType,
        addressLine = addressLine
    )

fun getPhoneNumberCreateRequest(
    personId: PersonId,
    phoneNumberType: PhoneNumberType = PhoneNumberType.Home,
    phone: String = "23423"
) =
    AddPhoneNumberRequest(
        personId = personId,
        phoneNumberType = phoneNumberType,
        phone = phone
    )

fun getPhoneNumberUpdateRequest(
    phoneNumberId: PhoneNumberId,
    personId: PersonId,
    phoneNumberType: PhoneNumberType = PhoneNumberType.Home,
    phone: String = "23423u"
)
        =
    UpdatePhoneNumberRequest(
        phoneNumberId = phoneNumberId,
        personId= personId,
        phoneNumberType = phoneNumberType,
        phone = phone
    )

fun getEmailCreateRequest(
    personId: PersonId,
    emailType: EmailType = EmailType.Home,
    emailAddress: String = "abc@email.com",
) =
    AddEmailRequest(
        personId = personId,
        emailType = emailType,
        emailAddress = emailAddress
    )

fun getEmailUpdateRequest(
    emailId: EmailId,
    personId: PersonId,
    emailType: EmailType = EmailType.Home,
    emailAddress: String = "abc@update.com"
)
        =
    UpdateEmailRequest(
        emailId = emailId,
        personId= personId,
        emailType = emailType,
        emailAddress = emailAddress
    )


fun getGroupCreateRequest1(
    groupName: String = "Friends",
) =
    AddGroupRequest(
        groupName = groupName,
    )

fun getGroupCreateRequest2(
    groupName: String = "Family",
) =
    AddGroupRequest(
        groupName = groupName,
    )

fun getGroupCreateRequest3(
    groupName: String = "Home",
) =
    AddGroupRequest(
        groupName = groupName,
    )

fun getGroupCreateRequest4(
    groupName: String = "School",
) =
    AddGroupRequest(
        groupName = groupName,
    )
