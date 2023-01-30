package com.addressbook.storages

import com.addressbook.tables.GroupPersonsAssociation
import com.addressbook.tables.GroupsTable
import com.example.addressbookdb.Group
import com.example.addressbookdb.GroupId
import com.example.addressbookdb.PersonId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object GroupDB {
    fun addGroup(group: Group): Group {
        transaction {
            GroupsTable.insert{
                it[this.groupId] = group.groupId
                it[this.groupName] = group.groupName
            }
        }
        return group
    }

    fun removeGroup(groupId: GroupId): String{
        transaction{
            GroupPersonsAssociation.deleteWhere { GroupPersonsAssociation.groupId eq groupId }
            GroupsTable.deleteWhere { GroupsTable.groupId eq groupId }
        }
        return "Group with group id as ${groupId} is deleted."
    }


    fun removePersonFromGroup(groupId: GroupId,personId: PersonId): String{
        transaction {
            GroupPersonsAssociation.
            deleteWhere {( GroupPersonsAssociation.personId eq personId) and (GroupPersonsAssociation.groupId eq groupId)}
        }
        return "Person with person id as ${personId} is deleted from group with group id as ${groupId}"
    }

    fun connectPersonsWithGroup(groupId: GroupId, personIds: List<PersonId> ){
        for(i in personIds){
            transaction {
                GroupPersonsAssociation.insert{
                    it[this.groupId] = groupId
                    it[this.personId] = i
                }
            }
        }
    }

    fun connectGroupsWithPerson(personId: PersonId, groupIds: List<GroupId> ){
        for(i in groupIds){
            transaction {
                GroupPersonsAssociation.insert{
                    it[this.groupId] = i
                    it[this.personId] = personId
                }
            }
        }
    }
}