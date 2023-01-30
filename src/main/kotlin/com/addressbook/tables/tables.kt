package com.addressbook.tables

import com.example.addressbookdb.requests.AddressType
import com.example.addressbookdb.requests.EmailType
import com.example.addressbookdb.requests.PhoneNumberType
import org.jetbrains.exposed.sql.Table

object Persons : Table() {
    val personId = uuid("person_id").autoGenerate()
    val firstName = varchar("first_name", length = 100)
    val lastName = varchar("last_name", length = 100)

    override val primaryKey = PrimaryKey(personId, name = "PK_Contact_ID")
}

object PhoneNumbers : Table() {
    val phoneNumberId = uuid("phone_number_id").autoGenerate()
    val personId = (uuid("person_id") references Persons.personId).index()
    val phoneNumberType = enumerationByName<PhoneNumberType>("type",10)
    val phone = varchar("phone_number", length = 12)

    override val primaryKey = PrimaryKey(phoneNumberId, name = "PK_PhoneNumber_ID")
}

object Emails : Table() {
    val emailId = uuid("email_id").autoGenerate()
    val personId = (uuid("person_id") references Persons.personId).index()
    val emailType = enumerationByName<EmailType>("type",10)
    val emailAddress = varchar("email_address", length = 100)

    override val primaryKey = PrimaryKey(emailId, name = "PK_Email_ID")
}

object Addresses : Table() {
    val addressId = uuid("address_id").autoGenerate()
    val personId = (uuid("person_id") references Persons.personId).index()
    val addressType =   enumerationByName<AddressType>("type",10)
    val addressLine = varchar("address_detail", length = 100)

    override val primaryKey = PrimaryKey(addressId, name = "PK_Address_ID")
}

object Groups : Table() {
    val groupId = uuid("group_id").autoGenerate()
    val personId = (uuid("person_id") references Persons.personId).index()
    val groupName = varchar("group_name", length = 10)

    override val primaryKey = PrimaryKey(groupId, name = "PK_Group_ID")
}