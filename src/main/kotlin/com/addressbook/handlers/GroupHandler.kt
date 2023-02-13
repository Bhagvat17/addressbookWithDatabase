package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.models.Group
import com.addressbook.commands.*
import com.addressbook.models.Person

object GroupHandler {
    fun addGroupHandler(cmd: AddGroupCommand): Either<Exception, Group> {
        return cmd.execute()
    }

    fun updateGroupHandler(cmd: UpdateGroupCommand): Either<Exception, Group> {
        return cmd.execute()
    }
    fun removeGroupHandler(cmd: RemoveGroupCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun removePersonFromGroupHandler(cmd: RemovePersonFromGroupCommand): Either<Exception, String> {
        return cmd.execute()
    }
    fun connectPersonsWithGroupHandler(cmd: ConnectPersonsWithGroupCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun connectGroupsWithPersonHandler(cmd: ConnectGroupsWithPersonCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun fetchAllGroupHandler(cmd: fetchAllGroupCommand): Either<Exception, List<Group>> {
        return cmd.execute()
    }

    fun fetchPersonsOfGroupHandler(cmd: fetchPersonsOfGroupCommand): Either<Exception, List<Person>> {
        return cmd.execute()
    }

    fun fetchGroupsOfPersonHandler(cmd: fetchGroupsOfPersonCommand): Either<Exception, List<Group>> {
        return cmd.execute()
    }
}