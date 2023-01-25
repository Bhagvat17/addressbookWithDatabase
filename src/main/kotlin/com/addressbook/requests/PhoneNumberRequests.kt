package com.example.addressbook.requests

import com.example.addressbook.PersonId
import java.util.*

enum class PhoneNumberType {
    Home,
    Office
}

data class AddPhoneNumberRequest(
    val personId: PersonId,
    val type: PhoneNumberType,
    val phone: String,
    )