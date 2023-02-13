package com.addressbook.apis.routes

import com.addressbook.connectToDatabase
import com.addressbook.entrypoints.*
import com.addressbook.models.AppContext
import com.addressbook.requests.AddEmailRequest
import com.addressbook.requests.UpdateEmailRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.emailRouting() {
    val dbCon = connectToDatabase()
    route("/emails") {
        get {
            val emailList = fetchAllEmailEntryPoint(AppContext(dbCon)).orNull()!!
            call.respond(emailList)
        }
        get("/{personName}") {
            val personName = call.parameters["personName"]
            val email = fetchEmailByPersonNameEntryPoint(AppContext(dbCon), personName!!).orNull()!!
            call.respond(email)
        }
        post(){
            val email = call.receive<AddEmailRequest>()
            var addedEmail = addEmailEntryPoint(AppContext(dbCon),email).orNull()!!
            call.respondText("Email with personID ${email.personId} stored correctly",status = HttpStatusCode.Created)
        }

        delete("/{emailId}"){
            val emailId = call.parameters["emailId"]
            removeEmailByEmailIdEntryPoint(AppContext(dbCon), UUID.fromString(emailId))
            call.respondText("Email with emailId ${emailId} deleted successfully",status = HttpStatusCode.OK)
        }

        put(){
            val email = call.receive<UpdateEmailRequest>()
            updateEmailEntryPoint(AppContext(dbCon),email)
            call.respondText("email with emailId ${email.emailId} updated successfully",status = HttpStatusCode.OK)
        }
    }
}