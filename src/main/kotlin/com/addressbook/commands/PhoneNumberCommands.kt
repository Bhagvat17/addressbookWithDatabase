package com.addressbook.commands

import com.addressbook.storages.PhoneNumberDB
import com.example.addressbook.PhoneNumber
import com.example.addressbook.requests.AddPhoneNumberRequest
import java.util.*

fun AddPhoneNumberRequest.toPhoneNumber() =
    PhoneNumber(
        phoneNumberId = UUID.randomUUID(),
        personId = this@toPhoneNumber.personId,
        phoneNumberType = this@toPhoneNumber.type,
        phone = this@toPhoneNumber.phone
    )

class AddPhoneNumberCommand(
    private val storage: PhoneNumberDB,
    private val request: AddPhoneNumberRequest
): Command {
    override fun execute(): PhoneNumber {
        val phoneNumber = request.toPhoneNumber()


        val phoneNumberDetail = PhoneNumberDB.addPhoneNumber(phoneNumber)

        return PhoneNumber(
            phoneNumberId= phoneNumberDetail.phoneNumberId,
            personId = phoneNumberDetail.personId,
            phoneNumberType = phoneNumberDetail.phoneNumberType,
            phone=phoneNumberDetail.phone
        )
    }
}