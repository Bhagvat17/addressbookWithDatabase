package com.addressbook.requests

import com.addressbook.PersonId
import com.addressbook.PhoneNumberId

enum class PhoneNumberType {
    Home,
    Office
}

data class AddPhoneNumberRequest(
    val personId: PersonId,
    val phoneNumberType: PhoneNumberType,
    val phone: String,
    )

data class UpdatePhoneNumberRequest(
    val phoneNumberId: PhoneNumberId,
    val personId: PersonId,
    val phoneNumberType: PhoneNumberType,
    val phone: String,
)
