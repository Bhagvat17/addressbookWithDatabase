package com.addressbook.storages

import arrow.core.Either
import com.addressbook.Address
import com.addressbook.AddressId
import com.addressbook.PersonId
import com.addressbook.tables.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object AddressDB {

    fun addAddress(address: Address): Either<Exception,Address> {
        return try {
            transaction {
                Addresses.insert {
                    it[this.addressId] = address.addressId!!
                    it[this.personId] = address.personId
                    it[this.addressType] = address.addressType
                    it[this.addressLine] = address.addressLine
                }
            }
            Either.Right(address)
        }
    catch (e: Exception){
    Either.Left(Exception("Error while adding address"))
}
    }
    fun updateAddress(address: Address): Either<Exception,Address> {
        return try{
            transaction{
                Addresses.update({ Addresses.personId eq address.personId}) {
                    it[this.addressType] = address.addressType
                    it[this.addressLine] = address.addressLine
                }
            }
            Either.Right(address)
        }
        catch (e: Exception) {
            Either.Left(Exception("Error while updating address"))
        }
    }

    fun removeAddressByPersonId(personId: PersonId): Either<Exception,String>{
        return try{
            transaction {
                Addresses.deleteWhere { Addresses.personId eq personId }
            }
            Either.Right("Person with first name as ${personId} is deleted.")
        }
        catch (e: Exception){
            Either.Left(Exception("Error while removing address"))
        }
    }

    fun removeAddressByAddressId(addressId: AddressId): Either<Exception,String>{
        return try{
            transaction {
                Addresses.deleteWhere { Addresses.addressId eq addressId }
            }
            Either.Right("Person with first name as ${addressId} is deleted.")
        }

        catch (e: Exception){
            Either.Left(Exception("Error while removing address"))
        }
    }

    fun listAllAddress(): Either<Exception,List<Address>>{
        return try{
            val result = transaction {
                Addresses.selectAll().map{
                        row -> Address(row[Addresses.addressId],row[Addresses.personId],row[Addresses.addressType], row[Addresses.addressLine])
                }
            }
            Either.Right(result)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while listing all address"))
        }
    }

    fun showAddressByPersonId(personId: PersonId): Either<Exception,List<Address>>{
        return try{
            val result = transaction {
                Addresses.select{ Addresses.personId eq personId}.map{
                        row -> Address(row[Addresses.addressId],row[Addresses.personId],row[Addresses.addressType], row[Addresses.addressLine])
                }
            }
            Either.Right(result)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while showing address by person id"))
        }
    }

    fun showAddressByPersonName(personName: String): Either<Exception,List<Address>>{
        return try{
            val result = transaction {
                (Addresses innerJoin Persons)
                    .select{ Persons.firstName eq personName}.map{
                            row -> Address(row[Addresses.addressId],row[Addresses.personId],row[Addresses.addressType], row[Addresses.addressLine])
                    }
            }
            Either.Right(result)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while showing address by person id"))
        }
    }
}