package com.addressbook.apis.routes

import com.addressbook.commands.fetchPersonByPersonNameCommand
import com.addressbook.connectToDatabase
import com.addressbook.entrypoints.*
import com.addressbook.models.AppContext
import com.addressbook.models.Contact
import com.addressbook.requests.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.personRouting() {
    val dbCon = connectToDatabase()
    route("/persons") {
        get {
                val personlist = fetchAllPersonEntryPoint(AppContext(dbCon)).orNull()!!
                call.respond(personlist)
        }
        get("/{personName}") {
            val personName = call.parameters["personName"]
            val person = fetchPersonByPersonNameEntryPoint(AppContext(dbCon), personName!!).orNull()!!
            call.respond(person)
        }
        post{
            val person = call.receive<AddPersonRequest>()
            var addedPerson = addPersonEntryPoint(AppContext(dbCon), AddPersonRequest(person.firstName,person.lastName)).orNull()!!
            println("post person finished")
            call.respondText("Person with personID ${addedPerson.personId} stored correctly",status = HttpStatusCode.Created)
        }

        delete("/{personId}"){
            val personId = call.parameters["personId"]
            removePersonEntryPoint(AppContext(dbCon), UUID.fromString(personId))
            call.respondText("Person with personID ${personId} deleted successfully",status = HttpStatusCode.OK)
        }

        put(){
            val person = call.receive<UpdatePersonRequest>()
            updatePersonEntryPoint(AppContext(dbCon),UpdatePersonRequest(person.personId,person.firstName,person.lastName))
            call.respondText("Person with personID ${person.personId} updated successfully",status = HttpStatusCode.OK)
        }
    }
}