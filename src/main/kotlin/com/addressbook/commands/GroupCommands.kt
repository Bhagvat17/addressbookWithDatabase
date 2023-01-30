package com.addressbook.commands

import com.addressbook.requests.AddGroupRequest
import com.addressbook.requests.UpdateGroupRequest
import com.addressbook.storages.GroupDB
import com.addressbook.storages.PhoneNumberDB
import com.example.addressbookdb.Group
import com.example.addressbookdb.GroupId
import com.example.addressbookdb.PersonId
import com.example.addressbookdb.PhoneNumber
import com.example.addressbookdb.requests.UpdatePhoneNumberRequest
import java.util.*

fun AddGroupRequest.toGroup() =
    Group(
        groupId = UUID.randomUUID(),
        groupName = this@toGroup.groupName,
    )

class AddGroupCommand(
    private val storage: GroupDB,
    private val request: AddGroupRequest
): Command {
    override fun execute(): Group {
        val group = request.toGroup()


        val groupDetail = storage.addGroup(group)

        return Group(
            groupId= groupDetail.groupId,
            groupName = groupDetail.groupName,
        )
    }
}


class ConnectPersonsWithGroup(
    private val groupId: GroupId,
    private val personIds: List<PersonId>
): Command {
    override fun execute(): Any {
        return GroupDB.connectPersonsWithGroup(groupId, personIds)
    }
}

class ConnectGroupsWithPerson(
    private val personId: PersonId,
    private val groupIds: List<GroupId>
): Command {
    override fun execute(): Any {
        return GroupDB.connectGroupsWithPerson(personId, groupIds)
    }
}

class RemoveGroupCommand(
    private val groupId: GroupId,
): Command{
    override fun execute(): Any {
        return GroupDB.removeGroup(groupId)
    }
}


class RemovePersonFromGroupCommand(
    private val groupId: GroupId,
    private val personId: PersonId
): Command{
    override fun execute(): Any {
        return GroupDB.removePersonFromGroup(groupId, personId)
    }
}