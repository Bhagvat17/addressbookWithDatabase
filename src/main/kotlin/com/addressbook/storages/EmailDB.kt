package com.addressbook.storages

import com.addressbook.tables.Emails
import com.addressbook.tables.Persons
import com.example.addressbookdb.Email
import com.example.addressbookdb.EmailId
import com.example.addressbookdb.PersonId
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object EmailDB {
    fun addEmail(email: Email): Email {
        transaction {
            Emails.insert{
                it[this.emailId] = email.emailId!!
                it[this.personId] = email.personId
                it[this.emailType] = email.emailType
                it[this.emailAddress] = email.emailAddress
            }
        }
        return email
    }

    fun updateEmail(email: Email): Email {
        transaction{
            Emails.update({ Emails.personId eq email.personId}) {
                it[this.emailType] = email.emailType
                it[this.emailAddress] = email.emailAddress
            }
        }
        return email
    }

    fun removeEmailByPersonId(personId: PersonId): String{
        transaction {
            Emails.deleteWhere { Emails.personId eq personId }
        }
        return "Person with first name as ${personId} is deleted."
    }

    fun removeEmailByEmailId(emailId: EmailId): String{
        transaction {
            Emails.deleteWhere { Emails.emailId eq emailId }
        }
        return "Person with first name as ${emailId} is deleted."
    }

    fun showAllEmail(): List<Email>{
        val result = transaction {
            Emails.selectAll().map{
                    row -> Email(row[Emails.emailId],row[Emails.personId],row[Emails.emailType], row[Emails.emailAddress])
            }
        }
        return result
    }

    fun showEmailByPersonId(personId: PersonId): List<Email>{
        val result = transaction {
            Emails.select{ Emails.personId eq personId}.map{
                    row -> Email(row[Emails.emailId],row[Emails.personId],row[Emails.emailType], row[Emails.emailAddress])
            }
        }
        return result
    }

    fun showEmailByPersonName(personName: String): List<Email>{
        val result = transaction {
            (Emails innerJoin Persons)
            Emails.select{ Persons.firstName eq personName}.map{
                    row -> Email(row[Emails.emailId],row[Emails.personId],row[Emails.emailType], row[Emails.emailAddress])
            }
        }
        return result
    }
}