package com.addressbook.repos

import arrow.core.Either
import com.addressbook.models.Email
import com.addressbook.models.EmailId
import com.addressbook.models.PersonId
import com.addressbook.tables.Emails
import com.addressbook.tables.Persons
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object EmailRepo {
    fun addEmail(email: Email): Either<Exception, Email> {
        return try{
            transaction {
            Emails.insert {
                it[this.emailId] = email.emailId!!
                it[this.personId] = email.personId
                it[this.emailType] = email.emailType
                it[this.emailAddress] = email.emailAddress
            }
        }
        Either.Right(email)
    }
    catch (e: Exception){
        Either.Left(Exception("Error while adding email"))
    }
    }

    fun updateEmail(email: Email): Either<Exception, Email> {
        return try{
            transaction{
            Emails.update({ Emails.emailId eq email.emailId}) {
                it[this.emailType] = email.emailType
                it[this.emailAddress] = email.emailAddress
            }
        }
        Either.Right(email)
    }
    catch (e: Exception){
        Either.Left(Exception("Error while updating email"))
    }
    }

    fun removeEmailByPersonId(personId: PersonId): Either<Exception,String>{
        return try{
            transaction {
            Emails.deleteWhere { Emails.personId eq personId }
        }
        Either.Right("Person with first name as ${personId} is deleted.")
    }
    catch (e: Exception){
        Either.Left(Exception("Error while removing email"))
    }
    }

    fun removeEmailByEmailId(emailId: EmailId): Either<Exception,String>{
        return try{
            transaction {
                Emails.deleteWhere { Emails.emailId eq emailId }
            }
            Either.Right("Person with first name as ${emailId} is deleted.")
        }

    catch (e: Exception){
        Either.Left(Exception("Error while removing email"))
    }
    }

    fun fetchAllEmail(): Either<Exception,List<Email>>{
        return try{
            val result = transaction {
            Emails.selectAll().map{
                    row -> Email(row[Emails.emailId],row[Emails.personId],row[Emails.emailType], row[Emails.emailAddress])
            }
        }
        Either.Right(result)
    }
    catch (e: Exception){
        Either.Left(Exception("Error while listing all email"))
    }
    }

    fun fetchEmailByPersonId(personId: PersonId): Either<Exception,List<Email>>{
        return try{
            val result = transaction {
            Emails.select{ Emails.personId eq personId}.map{
                    row -> Email(row[Emails.emailId],row[Emails.personId],row[Emails.emailType], row[Emails.emailAddress])
            }
        }
        Either.Right(result)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while showing email by person id"))
        }
    }

    fun fetchEmailByPersonName(personName: String): Either<Exception,List<Email>>{
        return try{
            val result = transaction {
            (Emails innerJoin Persons)
                .select{ Persons.firstName eq personName}.map{
                    row -> Email(row[Emails.emailId],row[Emails.personId],row[Emails.emailType], row[Emails.emailAddress])
            }
        }
        Either.Right(result)
    }
    catch (e: Exception){
        Either.Left(Exception("Error while showing email by person id"))
    }
    }
}