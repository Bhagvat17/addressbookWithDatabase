package com.addressbook

import com.addressbook.tables.*
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

internal fun connectToDatabase(): Database =
    Database.connect(
        HikariDataSource().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/addressBookDB"
            username = "bhagvat"
            password = "Bhagvat17@"
            isAutoCommit = false
            maximumPoolSize = 5
        }
    )

val schema = listOf<Table>(
    Persons,
    PhoneNumbers,
    Emails,
    Addresses,
    GroupsTable,
    GroupPersonsAssociation,
)

internal fun resetDatabase() {
    transaction {
//        schema.reversed().forEach{
//            SchemaUtils.drop(it)
//        }

        schema.forEach {
            SchemaUtils.create(it)
        }
    }
}