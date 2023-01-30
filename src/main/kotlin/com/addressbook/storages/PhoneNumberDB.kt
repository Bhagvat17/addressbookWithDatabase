package com.addressbook.storages

import com.addressbook.tables.PhoneNumbers
import com.example.addressbookdb.PersonId
import com.example.addressbookdb.PhoneNumber
import com.example.addressbookdb.PhoneNumberId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object PhoneNumberDB {
    fun addPhoneNumber(phoneNumber: PhoneNumber): PhoneNumber {
        transaction {
            PhoneNumbers.insert{
                it[this.phoneNumberId] = phoneNumber.phoneNumberId!!
                it[this.personId] = phoneNumber.personId
                it[this.phoneNumberType] = phoneNumber.phoneNumberType
                it[this.phone] = phoneNumber.phone
            }
        }
        return phoneNumber
    }

    fun updatePhoneNumber(phoneNumber: PhoneNumber): PhoneNumber {
        transaction {
            PhoneNumbers.update({ PhoneNumbers.personId eq phoneNumber.personId }) {
                it[this.phoneNumberType] = phoneNumber.phoneNumberType
                it[this.phone] = phoneNumber.phone
            }
        }
        return phoneNumber
    }

    fun removePhoneNumberByPersonId(personId: PersonId): String{
        transaction {
            PhoneNumbers.deleteWhere { PhoneNumbers.personId eq personId }
        }
        return "Person with first name as ${personId} is deleted."
    }

    fun removePhoneNumberByPhoneNumberId(phoneNumberId: PhoneNumberId): String{
        transaction {
            PhoneNumbers.deleteWhere { PhoneNumbers.phoneNumberId eq phoneNumberId }
        }
        return "Person with first name as ${phoneNumberId} is deleted."
    }
}