package com.example.addressbookdb.requests

import com.example.addressbookdb.PersonId
import com.example.addressbookdb.PhoneNumberId

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

data class RemovePhoneNumberRequest(
    val phoneNumberId: PhoneNumberId,
    val personId: PersonId,
    val phoneNumberType: PhoneNumberType,
    val phone: String,
)