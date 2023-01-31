package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.*
import com.addressbook.commands.*
import com.addressbook.requests.*

fun addGroupEntryPoint(
    ac: AppContext,
    req: AddGroupRequest
): Either<Exception, Group> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddGroupCommand(cmdCtx,req)
    return cmd.execute()
}

fun removeGroupEntryPoint(
    ac: AppContext,
    groupId: GroupId
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveGroupCommand(cmdCtx,groupId)
    return cmd.execute()
}

fun removePersonFromGroupEntryPoint(
    ac: AppContext,
    groupId: GroupId,
    personId: PersonId
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemovePersonFromGroupCommand(cmdCtx,groupId,personId)
    return cmd.execute()
}

fun connectPersonsWithGroupEntryPoint(
    ac: AppContext,
    groupId: GroupId,
    personIds: List<PersonId>
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ConnectPersonsWithGroupCommand(cmdCtx,groupId,personIds)
    return cmd.execute()
}

fun connectGroupsWithPersonEntryPoint(
    ac: AppContext,
    personId: PersonId,
    groupIds: List<GroupId>
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ConnectGroupsWithPersonCommand(cmdCtx,personId, groupIds)
    return cmd.execute()
}

fun listAllGroupsEntryPoint(
    ac: AppContext
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ListAllGroupsCommand(cmdCtx)
    return cmd.execute()
}

fun showPersonsOfGroupEntryPoint(
    ac: AppContext,
    groupId: GroupId
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ShowPersonsOfGroupCommand(cmdCtx,groupId)
    return cmd.execute()
}

fun showGroupsOfPersonEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception, Any> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ShowGroupsOfPersonCommand(cmdCtx,personId)
    return cmd.execute()
}

