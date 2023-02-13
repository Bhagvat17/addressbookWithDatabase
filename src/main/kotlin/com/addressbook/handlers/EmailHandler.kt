package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.models.Email
import com.addressbook.commands.*

object EmailHandler{
    fun addEmailHandler(cmd: AddEmailCommand): Either<Exception, Email> {
        return cmd.execute()
    }

    fun updateEmailHandler(cmd: UpdateEmailCommand): Either<Exception, Email> {
        return cmd.execute()
    }

    fun removeEmailByPersonIdHandler(cmd: RemoveEmailByPersonIdCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun removeEmailByEmailIdHandler(cmd: RemoveEmailByEmailIdCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun fetchAllEmailHandler(cmd: fetchAllEmailCommand): Either<Exception,List<Email>> {
        return cmd.execute()
    }

    fun fetchEmailByPersonIdHandler(cmd: fetchEmailByPersonIdCommand): Either<Exception,List<Email>>{
        return cmd.execute()
    }

    fun fetchEmailByPersonNameHandler(cmd: fetchEmailByPersonNameCommand): Either<Exception,List<Email>>{
        return cmd.execute()
    }
}