package com.addressbook

import com.addressbook.tables.*
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

fun connectToDatabase(): Database =
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

fun resetDatabase() {
    transaction {
        println("================")
        schema.reversed().forEach{
            println(it.tableName)
            SchemaUtils.drop(it)
        }

        println("----------------------")
        schema.forEach {
            println(it.tableName)
            SchemaUtils.create(it)
        }
    }
}