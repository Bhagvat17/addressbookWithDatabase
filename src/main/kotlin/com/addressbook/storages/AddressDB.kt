package com.addressbook.storages

import com.addressbook.tables.*
import com.example.addressbookdb.Address
import com.example.addressbookdb.AddressId
import com.example.addressbookdb.PersonId
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object AddressDB {

    fun addAddress(address: Address): Address {
        transaction {
            Addresses.insert{
                it[this.addressId] = address.addressId!!
                it[this.personId] = address.personId
                it[this.addressType] = address.addressType
                it[this.addressLine] = address.addressLine
            }
        }
        return address
    }
    fun updateAddress(address: Address): Address {
        transaction{
            Addresses.update({ Addresses.personId eq address.personId}) {
                it[this.addressType] = address.addressType
                it[this.addressLine] = address.addressLine
            }
        }
        return address
    }

    fun removeAddressByPersonID(personId: PersonId): String{
        transaction {
            Addresses.deleteWhere { Addresses.personId eq personId }
        }
        return "Person with first name as ${personId} is deleted."
    }

    fun removeAddressByAddressId(addressId: AddressId): String{
        transaction {
            Addresses.deleteWhere { Addresses.addressId eq addressId }
        }
        return "Person with first name as ${addressId} is deleted."
    }

    fun showAllAddress(): List<Address>{
        val result = transaction {
            Addresses.selectAll().map{
                    row -> Address(row[Addresses.addressId],row[Addresses.personId],row[Addresses.addressType], row[Addresses.addressLine])
            }
        }
        return result
    }

    fun showAddressByPersonId(personId: PersonId): List<Address>{
        val result = transaction {
            Addresses.select{ Addresses.personId eq personId}.map{
                    row -> Address(row[Addresses.addressId],row[Addresses.personId],row[Addresses.addressType], row[Addresses.addressLine])
            }
        }
        return result
    }

    fun showAddressByPersonName(personName: String): List<Address>{
        val result = transaction {
            (Addresses innerJoin Persons)
            Addresses.select{ Persons.firstName eq personName}.map{
                    row -> Address(row[Addresses.addressId],row[Addresses.personId],row[Addresses.addressType], row[Addresses.addressLine])
            }
        }
        return result
    }
}