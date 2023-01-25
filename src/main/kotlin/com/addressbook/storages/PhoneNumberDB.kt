package com.addressbook.storages

import com.addressbook.tables.PhoneNumbers
import com.example.addressbook.PhoneNumber
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object PhoneNumberDB {
    fun addPhoneNumber(phoneNumber: PhoneNumber): PhoneNumber {
        transaction {
            PhoneNumbers.insert{
                it[this.phoneNumberId] = phoneNumber.phoneNumberId
                it[this.personId] = phoneNumber.personId
                it[this.phoneNumberType] = phoneNumber.phoneNumberType
                it[this.phone] = phoneNumber.phone
            }
        }
        return phoneNumber
    }
}