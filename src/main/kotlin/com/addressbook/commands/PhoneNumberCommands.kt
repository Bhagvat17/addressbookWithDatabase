package com.addressbook.commands

import com.addressbook.storages.PhoneNumberDB
import com.example.addressbookdb.PersonId
import com.example.addressbookdb.PhoneNumber
import com.example.addressbookdb.PhoneNumberId
import com.example.addressbookdb.requests.AddPhoneNumberRequest
import com.example.addressbookdb.requests.UpdatePhoneNumberRequest
import java.util.*

fun AddPhoneNumberRequest.toPhoneNumber() =
    PhoneNumber(
        phoneNumberId = UUID.randomUUID(),
        personId = this@toPhoneNumber.personId,
        phoneNumberType = this@toPhoneNumber.phoneNumberType,
        phone = this@toPhoneNumber.phone
    )

fun UpdatePhoneNumberRequest.toPhoneNumber() =
    PhoneNumber(
        phoneNumberId = this@toPhoneNumber.phoneNumberId,
        personId = this@toPhoneNumber.personId,
        phoneNumberType = this@toPhoneNumber.phoneNumberType,
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

class UpdatePhoneNumberCommand(
    private val storage: PhoneNumberDB,
    private val request: UpdatePhoneNumberRequest
) : Command {
    override fun execute(): PhoneNumber {
        val phoneNumber = request.toPhoneNumber()
        return storage.updatePhoneNumber(phoneNumber)
    }
}

class RemovePhoneNumberByPersonIdCommand(
    private val storage: PhoneNumberDB,
    private val personId: PersonId,
) : Command {
    override fun execute(): Any {
        storage.removePhoneNumberByPersonId(personId)
        return " phoneNumber deleted"
    }
}

class RemovePhoneNumberByPhoneNumberIdCommand(
    private val storage: PhoneNumberDB,
    private val phoneNumberId: PhoneNumberId,
) : Command {
    override fun execute(): Any {
        storage.removePhoneNumberByPhoneNumberId(phoneNumberId)
        return " phoneNumber deleted"
    }
}