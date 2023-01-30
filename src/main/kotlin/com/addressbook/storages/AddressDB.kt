package com.addressbook.storages

import com.addressbook.tables.*
import com.example.addressbookdb.Address
import com.example.addressbookdb.AddressId
import com.example.addressbookdb.PersonId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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
}