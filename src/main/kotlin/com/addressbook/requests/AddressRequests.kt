package com.addressbook.requests

import com.addressbook.models.AddressId
import com.addressbook.models.PersonId


enum class AddressType {
    Home,
    Office
}

data class AddAddressRequest(
    val personId: PersonId,
    val addressType: String,
    val addressLine: String,
)

data class UpdateAddressRequest(
    val addressId: AddressId,
    val personId: PersonId,
    val addressType: String,
    val addressLine: String,
)
