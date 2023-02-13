package com.addressbook.requests

import com.addressbook.models.PersonId
import com.addressbook.models.PhoneNumberId

enum class PhoneNumberType {
    Home,
    Office
}

data class AddPhoneNumberRequest(
    val personId: PersonId,
    val phoneNumberType: String,
    val phone: String,
    )

data class UpdatePhoneNumberRequest(
    val phoneNumberId: PhoneNumberId,
    val personId: PersonId,
    val phoneNumberType: String,
    val phone: String,
)
