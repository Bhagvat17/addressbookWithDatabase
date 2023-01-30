package com.addressbook.storages

import com.addressbook.tables.*
import com.example.addressbookdb.Person
import com.example.addressbookdb.PersonId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object PersonDB {
    fun addPerson(person: Person): Person {
        transaction {
            Persons.insert{
                it[this.personId] = person.personId
                it[this.firstName] = person.firstName
                it[this.lastName] = person.lastName
            }
        }
        return person
    }
    fun updatePerson(person: Person): Person {
        transaction{
            Persons.update({ Persons.personId eq person.personId}) {
                it[this.firstName] = person.firstName
                it[this.lastName] = person.lastName
            }
        }
        return person
    }


    fun removePerson(personId: PersonId): String{
        transaction {
            PhoneNumbers.deleteWhere { PhoneNumbers.personId eq personId }
            Emails.deleteWhere { Emails.personId eq personId }
            Addresses.deleteWhere { Addresses.personId eq personId }
            GroupPersonsAssociation.deleteWhere { GroupPersonsAssociation.personId eq personId }
            Persons.deleteWhere { Persons.personId eq personId }
        }
        return "Person with first name as ${personId} is deleted."
    }

    fun listAllPerson(): List<Person>{
        val result = transaction {
            Persons.selectAll().map{
                row -> Person(row[Persons.personId],row[Persons.firstName],row[Persons.lastName])
            }
        }
        return result
    }

}