package com.addressbook.handlers

import arrow.core.Either
import com.addressbook.models.PhoneNumber
import com.addressbook.commands.*

object PhoneNumberHandler{
    fun addPhoneNumberHandler(cmd: AddPhoneNumberCommand): Either<Exception, PhoneNumber> {
        return cmd.execute()
    }

    fun updatePhoneNumberHandler(cmd: UpdatePhoneNumberCommand): Either<Exception, PhoneNumber> {
        return cmd.execute()
    }

    fun removePhoneNumberByPersonIdHandler(cmd: RemovePhoneNumberByPersonIdCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun removePhoneNumberByPhoneNumberIdHandler(cmd: RemovePhoneNumberByPhoneNumberIdCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun fetchAllPhoneNumberHandler(cmd: fetchAllPhoneNumberCommand): Either<Exception, List<PhoneNumber>> {
        return cmd.execute()
    }

    fun fetchPhoneNumberByPersonIdHandler(cmd: fetchPhoneNumberByPersonIdCommand): Either<Exception, List<PhoneNumber>> {
        return cmd.execute()
    }

    fun fetchPhoneNumberByPersonNameHandler(cmd: fetchPhoneNumberByPersonNameCommand): Either<Exception, List<PhoneNumber>> {
        return cmd.execute()
    }
}