package com.addressbook.repos

import arrow.core.Either
import com.addressbook.models.Person
import com.addressbook.models.PersonId
import com.addressbook.tables.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object PersonRepo {
    fun addPerson(person: Person): Either<Exception, Person> {
        return try{
            transaction {
                Persons.insert{
                    it[this.personId] = person.personId
                    it[this.firstName] = person.firstName
                    it[this.lastName] = person.lastName
                }
            }
            Either.Right(person)
        }catch (e: Exception){
            Either.Left(Exception("Error while adding person"))
        }
    }
    fun updatePerson(person: Person): Either<Exception, Person> {
        return try{
            transaction{
                Persons.update({ Persons.personId eq person.personId}) {
                    it[this.firstName] = person.firstName
                    it[this.lastName] = person.lastName
                }
            }
            Either.Right(person)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while updating person"))
        }
    }


    fun removePerson(personId: PersonId): Either<Exception,String>{
        return try{
            transaction {
                PhoneNumbers.deleteWhere { PhoneNumbers.personId eq personId }
                Emails.deleteWhere { Emails.personId eq personId }
                Addresses.deleteWhere { Addresses.personId eq personId }
                GroupPersonsAssociation.deleteWhere { GroupPersonsAssociation.personId eq personId }
                Persons.deleteWhere { Persons.personId eq personId }
            }
            Either.Right("Person with first name as ${personId} is deleted.")
        }
        catch(e: Exception){
            Either.Left(Exception("Error while removing person"))
        }
    }

    fun fetchAllPerson(): Either<Exception,List<Person>>{
        return try {
            val result = transaction {
                Persons.selectAll().map { row ->
                    Person(row[Persons.personId], row[Persons.firstName], row[Persons.lastName])
                }
            }
            Either.Right(result)
        }
        catch(e: Exception){
            Either.Left(Exception("Error while listing all persons"))
        }
    }

    fun fetchPersonByPersonId(personId: PersonId):Either<Exception,Person>{
        return try {
            val result = transaction {
                Persons.select { Persons.personId eq personId }.map { row ->
                    Person(row[Persons.personId], row[Persons.firstName], row[Persons.lastName])
                }.first()
            }
            Either.Right(result)
        }
        catch(e: Exception){
            Either.Left(Exception("Error while showing person by person Id"))
        }
    }

    fun fetchPersonByPersonName(personName: String): Either<Exception,Person>{
        return try {
            val result = transaction {
                Persons.select { Persons.firstName eq personName }
                    .map { row -> Person(row[Persons.personId], row[Persons.firstName], row[Persons.lastName]) }
            }.first()
            Either.Right(result)
        }
        catch(e: Exception){
            Either.Left(Exception("Error while showing person by person name"))
        }
    }

}