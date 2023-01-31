package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.Group
import com.addressbook.commands.*

object GroupHandler {
    fun addGroupHandler(cmd: AddGroupCommand): Either<Exception, Group> {
        return cmd.execute()
    }

    fun removeGroupHandler(cmd: RemoveGroupCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun removePersonFromGroupHandler(cmd: RemovePersonFromGroupCommand): Either<Exception, Any> {
        return cmd.execute()
    }
    fun connectPersonsWithGroupHandler(cmd: ConnectPersonsWithGroupCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun connectGroupsWithPersonHandler(cmd: ConnectGroupsWithPersonCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun listAllGroupsHandler(cmd: ListAllGroupsCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun showPersonsOfGroupHandler(cmd: ShowPersonsOfGroupCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun showGroupsOfPersonHandler(cmd: ShowGroupsOfPersonCommand): Either<Exception, Any> {
        return cmd.execute()
    }
}