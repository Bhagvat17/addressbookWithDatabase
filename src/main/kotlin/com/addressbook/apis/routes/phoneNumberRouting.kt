package com.addressbook.apis.routes

import arrow.core.getOrElse
import com.addressbook.connectToDatabase
import com.addressbook.entrypoints.*
import com.addressbook.models.AppContext
import com.addressbook.requests.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.phoneRouting() {
    val dbCon = connectToDatabase()
    route("/phones") {
        get {
            val phoneNumberList = fetchAllPhoneNumberEntryPoint(AppContext(dbCon)).getOrElse { throw Exception("failed to list phoneNumbers") }
            call.respond(phoneNumberList)
        }
        get("/{personName}") {
            val personName = call.parameters["personName"]
            val phoneNumber = fetchPhoneNumberByPersonNameEntryPoint(AppContext(dbCon), personName!!).getOrElse { throw Exception("failed to list phoneNumber by person name") }
            call.respond(phoneNumber)
        }
        post(){
            println("hello add phone Number")
            val phoneNumber = call.receive<AddPhoneNumberRequest>()
            println("hello 2")
            var addedPhoneNumber = addPhoneNumberEntryPoint(AppContext(dbCon),phoneNumber).getOrElse { throw Exception("failed to add phoneNumber") }
            call.respondText("PhoneNumber with personID ${phoneNumber.personId} stored correctly",status = HttpStatusCode.OK)
        }

        delete("/{phoneNumberId}"){
            val phoneNumberId = call.parameters["phoneNumberId"]
            removePhoneNumberByPhoneNumberIdEntryPoint(AppContext(dbCon), UUID.fromString(phoneNumberId))
            call.respondText("PhoneNumber with phoneNumberId ${phoneNumberId} deleted successfully",status = HttpStatusCode.OK)
        }

        put(){
            val phoneNumber = call.receive<UpdatePhoneNumberRequest>()
            updatePhoneNumberEntryPoint(AppContext(dbCon),phoneNumber)
            call.respondText("phoneNumber with phoneNumberId ${phoneNumber.phoneNumberId} updated successfully",status = HttpStatusCode.OK)
        }
    }
}