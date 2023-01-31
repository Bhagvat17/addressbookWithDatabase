package com.addressbook.commands

import arrow.core.Either
import com.addressbook.CommandContext
import com.addressbook.Group
import com.addressbook.GroupId
import com.addressbook.PersonId
import com.addressbook.requests.AddGroupRequest
import com.addressbook.storages.GroupDB
import java.util.*

fun AddGroupRequest.toGroup() =
    Group(
        groupId = UUID.randomUUID(),
        groupName = this@toGroup.groupName,
    )

class AddGroupCommand(
    val cmdCtx: CommandContext,
    private val request: AddGroupRequest
): Command {
    override fun execute(): Either<Exception, Group> = GroupDB.addGroup(request.toGroup())

}

class RemoveGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: GroupId,
): Command{
    override fun execute(): Either<Exception, Any> = GroupDB.removeGroup(groupId)
}

class RemovePersonFromGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: GroupId,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, Any> = GroupDB.removePersonFromGroup(groupId, personId)
}

class ConnectPersonsWithGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: GroupId,
    private val personIds: List<PersonId>
): Command {
    override fun execute(): Either<Exception, Any> = GroupDB.connectPersonsWithGroup(groupId, personIds)
}

class ConnectGroupsWithPersonCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
    private val groupIds: List<GroupId>
): Command {
    override fun execute(): Either<Exception, Any> = GroupDB.connectGroupsWithPerson(personId, groupIds)
}

class ListAllGroupsCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception, Any> = GroupDB.listAllGroups()
}

class ShowPersonsOfGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: GroupId
): Command{
    override fun execute(): Either<Exception, Any> = GroupDB.showPersonsOfGroup(groupId)
}


class ShowGroupsOfPersonCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, Any> = GroupDB.showGroupsOfPersons(personId)
}