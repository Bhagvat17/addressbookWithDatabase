package com.addressbook.storages

import arrow.core.Either
import com.addressbook.*
import com.addressbook.tables.GroupPersonsAssociation
import com.addressbook.tables.GroupsTable
import com.addressbook.tables.Persons
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object GroupDB {
    fun addGroup(group: Group): Either<Exception,Group> {
        return try{
            transaction {
                GroupsTable.insert{
                    it[this.groupId] = group.groupId
                    it[this.groupName] = group.groupName
                }
            }
            Either.Right(group)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while adding group"))
        }
    }

    fun removeGroup(groupId: GroupId): Either<Exception,String>{
        return try {
            transaction {
                GroupPersonsAssociation.deleteWhere { GroupPersonsAssociation.groupId eq groupId }
                GroupsTable.deleteWhere { GroupsTable.groupId eq groupId }
            }
            Either.Right("Group with group id as ${groupId} is deleted.")
        }
        catch (e: Exception){
            Either.Left(Exception("Error while removing group"))
        }
    }


    fun removePersonFromGroup(groupId: GroupId,personId: PersonId): Either<Exception,String> {
        return try {
            transaction {
                GroupPersonsAssociation.deleteWhere { (GroupPersonsAssociation.personId eq personId) and (GroupPersonsAssociation.groupId eq groupId) }
            }
            Either.Right("Person with person id as ${personId} is deleted from group with group id as ${groupId}")
        }
        catch (e: Exception) {
            Either.Left(Exception("Error while removing person from group"))
        }
    }

    fun connectPersonsWithGroup(groupId: GroupId, personIds: List<PersonId>): Either<Exception,String>{
        return try{
            for(i in personIds){
                transaction {
                    GroupPersonsAssociation.insert{
                        it[this.groupId] = groupId
                        it[this.personId] = i
                    }
                }
            }
            Either.Right("Persons added to the Group")
        }
        catch (e: Exception) {
            Either.Left(Exception("Error while adding persons to group"))
        }
    }

    fun connectGroupsWithPerson(personId: PersonId, groupIds: List<GroupId>): Either<Exception,String>{
        return try{
            for(i in groupIds){
                transaction {
                    GroupPersonsAssociation.insert{
                        it[this.groupId] = i
                        it[this.personId] = personId
                    }
                }
            }
            Either.Right("Groups added to the Person")
        }
        catch (e: Exception) {
            Either.Left(Exception("Error while adding groups to persons"))
        }
    }

    fun listAllGroups(): Either<Exception,List<Group>>{
        return try{
            val result = transaction {
                GroupsTable.selectAll().map{
                        row -> Group(row[GroupsTable.groupId],row[GroupsTable.groupName])
                }
            }
            Either.Right(result)
        }
        catch (e: Exception){
            Either.Left(Exception("Error while listing all groups"))
        }
    }

    fun showPersonsOfGroup(groupId: GroupId): Either<Exception,List<Person>> {
        return try{
            val result = transaction {
            (GroupPersonsAssociation innerJoin Persons)
                .select { GroupPersonsAssociation.groupId eq groupId }
                .map { row ->
                    Person(row[Persons.personId], row[Persons.firstName], row[Persons.lastName])
                }
        }
        Either.Right(result)
    }
        catch (e: Exception){
            Either.Left(Exception("Error while showing persons of group"))
        }
    }

    fun showGroupsOfPersons(personId: PersonId): Either<Exception,List<Group>> {
        return  try {
            val result = transaction {
            (GroupPersonsAssociation innerJoin GroupsTable)
                .select { GroupPersonsAssociation.personId eq personId }
                .map { row ->
                    Group(row[GroupsTable.groupId], row[GroupsTable.groupName])
                }
        }
        Either.Right(result)
    }
        catch (e: Exception){
            Either.Left(Exception("Error while showing persons of group"))
        }
    }
}