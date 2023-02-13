package com.addressbook.apis.routes

import com.addressbook.connectToDatabase
import com.addressbook.entrypoints.*
import com.addressbook.models.AppContext
import com.addressbook.models.PersonId
import com.addressbook.requests.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.groupRouting() {
    val dbCon = connectToDatabase()
    route("/groups") {
        get {
            val groupList = fetchAllGroupEntryPoint(AppContext(dbCon)).orNull()!!
            call.respond(groupList)
        }
        post(){
            val group = call.receive<AddGroupRequest>()
            var addedGroup = addGroupEntryPoint(AppContext(dbCon),group).orNull()!!
            call.respondText("Group with groupId ${addedGroup.groupId} stored correctly",status = HttpStatusCode.Created)
        }
        delete("/{groupId}"){
            val groupId = call.parameters["groupId"]
            removeGroupEntryPoint(AppContext(dbCon), UUID.fromString(groupId))
            call.respondText("Group with groupId ${groupId} deleted successfully",status = HttpStatusCode.OK)
        }

        put(){
            val group = call.receive<UpdateGroupRequest>()
            updateGroupEntryPoint(AppContext(dbCon),group)
            call.respondText("group with groupId ${group.groupId} updated successfully",status = HttpStatusCode.OK)
        }
    }
}