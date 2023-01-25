package com.addressbook.storages

import com.addressbook.tables.Emails
import com.example.addressbook.Email
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object EmailDB {
    fun addEmail(email: Email): Email {
        transaction {
            Emails.insert{
                it[this.emailId] = email.emailId
                it[this.personId] = email.personId
                it[this.emailType] = email.emailType
                it[this.emailAddress] = email.emailAddress
            }
        }
        return email
    }
}