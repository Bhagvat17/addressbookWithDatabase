package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.Email
import com.addressbook.commands.*

object EmailHandler{
    fun addEmailHandler(cmd: AddEmailCommand): Either<Exception, Email> {
        return cmd.execute()
    }

    fun updateEmailHandler(cmd: UpdateEmailCommand): Either<Exception, Email> {
        return cmd.execute()
    }

    fun removeEmailByPersonIdHandler(cmd: RemoveEmailByPersonIdCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun removeEmailByEmailIdHandler(cmd: RemoveEmailByEmailIdCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun listAllEmailEntryPoint(cmd: ListAllEmailCommand): Either<Exception,Any> {
        return cmd.execute()
    }

    fun showEmailByPersonIdHandler(cmd: ShowEmailByPersonIdCommand): Either<Exception,Any>{
        return cmd.execute()
    }

    fun showEmailByPersonName(cmd: ShowEmailByPersonNameCommand): Either<Exception,Any>{
        return cmd.execute()
    }
}