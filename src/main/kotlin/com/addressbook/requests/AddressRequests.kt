package com.example.addressbookdb.requests

import com.example.addressbookdb.AddressId
import com.example.addressbookdb.PersonId
import java.util.*


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
