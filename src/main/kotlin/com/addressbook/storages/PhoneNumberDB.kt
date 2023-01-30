package com.addressbook.storages

import com.addressbook.tables.Persons
import com.addressbook.tables.PhoneNumbers
import com.example.addressbookdb.Person
import com.example.addressbookdb.PersonId
import com.example.addressbookdb.PhoneNumber
import com.example.addressbookdb.PhoneNumberId
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

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

    fun showAllPhoneNumber(): List<PhoneNumber>{
        val result = transaction {
            PhoneNumbers.selectAll().map{
                row -> PhoneNumber(row[PhoneNumbers.phoneNumberId],row[PhoneNumbers.personId],row[PhoneNumbers.phoneNumberType], row[PhoneNumbers.phone])
            }
        }
        return result
    }

    fun showPhoneNumberByPersonId(personId: PersonId): List<PhoneNumber>{
        val result = transaction {
            PhoneNumbers.select{PhoneNumbers.personId eq personId}.map{
                row -> PhoneNumber(row[PhoneNumbers.phoneNumberId],row[PhoneNumbers.personId],row[PhoneNumbers.phoneNumberType], row[PhoneNumbers.phone])
            }
        }
        return result
    }

    fun showPhoneNumberByPersonName(personName: String): List<PhoneNumber>{
        val result = transaction {
            (PhoneNumbers innerJoin Persons)
            PhoneNumbers.select{ Persons.firstName eq personName}.map{
                    row -> PhoneNumber(row[PhoneNumbers.phoneNumberId],row[PhoneNumbers.personId],row[PhoneNumbers.phoneNumberType], row[PhoneNumbers.phone])
            }
        }
        return result
    }
}