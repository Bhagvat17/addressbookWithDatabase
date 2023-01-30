package com.addressbook.storages

import com.addressbook.tables.Emails
import com.example.addressbookdb.Email
import com.example.addressbookdb.EmailId
import com.example.addressbookdb.PersonId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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
}