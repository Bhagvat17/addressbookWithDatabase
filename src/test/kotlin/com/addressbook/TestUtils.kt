package com.addressbook

import com.addressbook.models.AddressId
import com.addressbook.models.EmailId
import com.addressbook.models.PersonId
import com.addressbook.models.PhoneNumberId
import com.addressbook.requests.*


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
    addressType: String = "Home",
    addressLine: String = "Baroda",
) =
    AddAddressRequest(
        personId = personId,
        addressType = addressType,
        addressLine = addressLine
    )

fun getAddressCreateRequest2(
    personId: PersonId,
    addressType: String = "Home",
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
    addressType: String = "Home",
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
    phoneNumberType: String = "Home",
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
    phoneNumberType: String = "Home",
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
    emailType: String = "Home",
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
    emailType: String = "Home",
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
