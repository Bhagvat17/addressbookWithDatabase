package com.addressbook.commands

import arrow.core.Either
import com.addressbook.models.*
import com.addressbook.requests.AddGroupRequest
import com.addressbook.repos.GroupRepo
import com.addressbook.requests.UpdateGroupRequest
import java.util.*

fun AddGroupRequest.toGroup() =
    Group(
        groupId = UUID.randomUUID(),
        groupName = this@toGroup.groupName,
    )

fun UpdateGroupRequest.toGroup() =
    Group(
        groupId = this@toGroup.groupId,
        groupName = this@toGroup.groupName
    )

class AddGroupCommand(
    val cmdCtx: CommandContext,
    private val request: AddGroupRequest
): Command {
    override fun execute(): Either<Exception, Group> = GroupRepo.addGroup(request.toGroup())

}

class UpdateGroupCommand(
    val cmdCtx: CommandContext,
    private val request: UpdateGroupRequest
): Command{
    override fun execute(): Either<Exception,Group> = GroupRepo.updateGroup(request.toGroup())
}

class RemoveGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: GroupId,
): Command{
    override fun execute(): Either<Exception, String> = GroupRepo.removeGroup(groupId)
}

class RemovePersonFromGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: GroupId,
    private val personIds: List<PersonId>
): Command{
    override fun execute(): Either<Exception, String> = GroupRepo.removePersonFromGroup(groupId, personIds)
}

class ConnectPersonsWithGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: GroupId,
    private val personIds: List<PersonId>
): Command {
    override fun execute(): Either<Exception, String> = GroupRepo.connectPersonsWithGroup(groupId, personIds)
}

class ConnectGroupsWithPersonCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId,
    private val groupIds: List<GroupId>
): Command {
    override fun execute(): Either<Exception, String> = GroupRepo.connectGroupsWithPerson(personId, groupIds)
}

class fetchAllGroupCommand(
    val cmdCtx: CommandContext,
): Command{
    override fun execute(): Either<Exception, List<Group>> = GroupRepo.fetchAllGroup()
}

class fetchPersonsOfGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: GroupId
): Command{
    override fun execute(): Either<Exception, List<Person>> = GroupRepo.fetchPersonsOfGroup(groupId)
}


class fetchGroupsOfPersonCommand(
    val cmdCtx: CommandContext,
    private val personId: PersonId
): Command{
    override fun execute(): Either<Exception, List<Group>> = GroupRepo.fetchGroupsOfPersons(personId)
}