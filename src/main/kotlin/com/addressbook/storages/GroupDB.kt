package com.addressbook.storages

import com.addressbook.tables.Groups
import com.example.addressbookdb.Group
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object GroupDB {
    fun addGroup(group: Group): Group {
        transaction {
            Groups.insert{
                it[this.groupId] = group.groupId!!
                it[this.personId] = group.personId
                it[this.groupName] = group.groupName
            }
        }
        return group
    }

//    fun updateGroup(group: Group): Group {
//        transaction{
//            Groups.update({ Groups.personId eq group.personId}) {
//                it[this.groupName] = group.groupName
//            }
//        }
//        return group
//    }
}