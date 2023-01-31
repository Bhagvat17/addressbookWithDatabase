package com.addressbook.storages

import arrow.core.Either
import com.addressbook.PhoneNumber
import com.addressbook.PersonId
import com.addressbook.PhoneNumberId
import com.addressbook.tables.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object PhoneNumberDB {
    fun addPhoneNumber(phoneNumber: PhoneNumber): Either<Exception, PhoneNumber> {
        return try {
            transaction {
                PhoneNumbers.insert {
                    it[this.phoneNumberId] = phoneNumber.phoneNumberId!!
                    it[this.personId] = phoneNumber.personId
                    it[this.phoneNumberType] = phoneNumber.phoneNumberType
                    it[this.phone] = phoneNumber.phone
                }
            }
            Either.Right(phoneNumber)
        } catch (e: Exception) {
            Either.Left(Exception("Error while adding phone number"))
        }
    }

    fun updatePhoneNumber(phoneNumber: PhoneNumber): Either<Exception, PhoneNumber> {
        return try {
            transaction {
                PhoneNumbers.update({ PhoneNumbers.personId eq phoneNumber.personId }) {
                    it[this.phoneNumberType] = phoneNumber.phoneNumberType
                    it[this.phone] = phoneNumber.phone
                }
            }
            Either.Right(phoneNumber)
        } catch (e: Exception) {
            Either.Left(Exception("Error while updating phone number"))
        }
    }


    fun removePhoneNumberByPersonId(personId: PersonId): Either<Exception,String>{
        return try{
            transaction {
                PhoneNumbers.deleteWhere { PhoneNumbers.personId eq personId }
            }
            Either.Right("Person with first name as ${personId} is deleted.")
        }
        catch (e: Exception){
            Either.Left(Exception("Error while removing phoneNumber"))
        }
    }

    fun removePhoneNumberByPhoneNumberId(phoneNumberId: PhoneNumberId): Either<Exception,String>{
        return try{
            transaction {
                PhoneNumbers.deleteWhere { PhoneNumbers.phoneNumberId eq phoneNumberId }
            }
            Either.Right("Person with first name as ${phoneNumberId} is deleted.")
        }

        catch (e: Exception){
            Either.Left(Exception("Error while removing phoneNumber"))
        }
    }

    fun listAllPhoneNumber(): Either<Exception,List<PhoneNumber>>{
        return try{
            val result = transaction {
                PhoneNumbers.selectAll().map{
                        row -> PhoneNumber(row[PhoneNumbers.phoneNumberId],row[PhoneNumbers.personId],row[PhoneNumbers.phoneNumberType], row[PhoneNumbers.phone])
                }
            }
            Either.Right(result)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while listing all phoneNumber"))
        }
    }

    fun showPhoneNumberByPersonId(personId: PersonId): Either<Exception,List<PhoneNumber>>{
        return try{
            val result = transaction {
                PhoneNumbers.select{ PhoneNumbers.personId eq personId}.map{
                        row -> PhoneNumber(row[PhoneNumbers.phoneNumberId],row[PhoneNumbers.personId],row[PhoneNumbers.phoneNumberType], row[PhoneNumbers.phone])
                }
            }
            Either.Right(result)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while showing phoneNumber by person id"))
        }
    }

    fun showPhoneNumberByPersonName(personName: String): Either<Exception,List<PhoneNumber>>{
        return try{
            val result = transaction {
                (PhoneNumbers innerJoin Persons)
                    .select{ Persons.firstName eq personName}.map{
                            row -> PhoneNumber(row[PhoneNumbers.phoneNumberId],row[PhoneNumbers.personId],row[PhoneNumbers.phoneNumberType], row[PhoneNumbers.phone])
                    }
            }
            Either.Right(result)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while showing phoneNumber by person id"))
        }
    }
}
    