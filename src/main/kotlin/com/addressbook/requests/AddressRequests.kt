package com.addressbook.requests

import com.addressbook.AddressId
import com.addressbook.PersonId


enum class AddressType {
    Home,
    Office
}

data class AddAddressRequest(
    val personId: PersonId,
    val addressType: AddressType,
    val addressLine: String,
)

data class UpdateAddressRequest(
    val addressId: AddressId,
    val personId: PersonId,
    val addressType: AddressType,
    val addressLine: String,
)
