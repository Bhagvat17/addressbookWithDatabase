package com.addressbook.entrypoints

import arrow.core.Either
import com.addressbook.commands.*
import com.addressbook.handlers.GroupHandler.addGroupHandler
import com.addressbook.handlers.GroupHandler.connectGroupsWithPersonHandler
import com.addressbook.handlers.GroupHandler.connectPersonsWithGroupHandler
import com.addressbook.handlers.GroupHandler.fetchAllGroupHandler
import com.addressbook.handlers.GroupHandler.fetchGroupsOfPersonHandler
import com.addressbook.handlers.GroupHandler.fetchPersonsOfGroupHandler
import com.addressbook.handlers.GroupHandler.removeGroupHandler
import com.addressbook.handlers.GroupHandler.removePersonFromGroupHandler
import com.addressbook.handlers.GroupHandler.updateGroupHandler
import com.addressbook.models.*
import com.addressbook.requests.*

fun addGroupEntryPoint(
    ac: AppContext,
    req: AddGroupRequest
): Either<Exception, Group> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = AddGroupCommand(cmdCtx,req)
    return addGroupHandler(cmd)
}

fun updateGroupEntryPoint(
    ac: AppContext,
    req: UpdateGroupRequest
): Either<Exception, Group> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = UpdateGroupCommand(cmdCtx,req)
    return updateGroupHandler(cmd)
}

fun removeGroupEntryPoint(
    ac: AppContext,
    groupId: GroupId
): Either<Exception, String> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemoveGroupCommand(cmdCtx,groupId)
    return removeGroupHandler(cmd)
}

fun removePersonFromGroupEntryPoint(
    ac: AppContext,
    groupId: GroupId,
    personIds: List<PersonId>
): Either<Exception, String> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = RemovePersonFromGroupCommand(cmdCtx,groupId,personIds)
    return removePersonFromGroupHandler(cmd)
}

fun connectPersonsWithGroupEntryPoint(
    ac: AppContext,
    groupId: GroupId,
    personIds: List<PersonId>
): Either<Exception, String> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ConnectPersonsWithGroupCommand(cmdCtx,groupId,personIds)
    return connectPersonsWithGroupHandler(cmd)
}

fun connectGroupsWithPersonEntryPoint(
    ac: AppContext,
    personId: PersonId,
    groupIds: List<GroupId>
): Either<Exception, String> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = ConnectGroupsWithPersonCommand(cmdCtx,personId, groupIds)
    return connectGroupsWithPersonHandler(cmd)
}

fun fetchAllGroupEntryPoint(
    ac: AppContext
): Either<Exception, List<Group>> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchAllGroupCommand(cmdCtx)
    return fetchAllGroupHandler(cmd)
}

fun fetchPersonsOfGroupEntryPoint(
    ac: AppContext,
    groupId: GroupId
): Either<Exception, List<Person>> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchPersonsOfGroupCommand(cmdCtx,groupId)
    return fetchPersonsOfGroupHandler(cmd)
}

fun fetchGroupsOfPersonEntryPoint(
    ac: AppContext,
    personId: PersonId
): Either<Exception, List<Group>> {
    val cmdCtx= CommandContext(ac.db)
    val cmd = fetchGroupsOfPersonCommand(cmdCtx,personId)
    return fetchGroupsOfPersonHandler(cmd)
}

