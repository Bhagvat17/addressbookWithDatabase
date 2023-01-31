package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.PhoneNumber
import com.addressbook.commands.*

object PhoneNumberHandler{
    fun addPhoneNumberHandler(cmd: AddPhoneNumberCommand): Either<Exception, PhoneNumber> {
        return cmd.execute()
    }

    fun updatePhoneNumberHandler(cmd: UpdatePhoneNumberCommand): Either<Exception, PhoneNumber> {
        return cmd.execute()
    }

    fun removePhoneNumberByPersonIdHandler(cmd: RemovePhoneNumberByPersonIdCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun removePhoneNumberByPhoneNumberIdHandler(cmd: RemovePhoneNumberByPhoneNumberIdCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun listAllPhoneNumberEntryPoint(cmd: ListAllPhoneNumberCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun showPhoneNumberByPersonIdHandler(cmd: ShowPhoneNumberByPersonIdCommand): Either<Exception, Any> {
        return cmd.execute()
    }

    fun showPhoneNumberByPersonName(cmd: ShowPhoneNumberByPersonNameCommand): Either<Exception, Any> {
        return cmd.execute()
    }
}