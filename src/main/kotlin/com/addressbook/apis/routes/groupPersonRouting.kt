package com.addressbook.apis.routes

import arrow.core.getOrElse
import com.addressbook.connectToDatabase
import com.addressbook.entrypoints.*
import com.addressbook.models.AppContext
import com.addressbook.models.GroupId
import com.addressbook.models.PersonId
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.groupPersonRouting() {
    val dbCon = connectToDatabase()
    route("/groupPersons") {
        get("/{personId}/groups"){
            val personId = call.parameters["personId"]
            val groupList = fetchGroupsOfPersonEntryPoint(AppContext(dbCon),UUID.fromString(personId)).getOrElse { throw Exception("error while showing groups of person") }
            call.respond(groupList)
        }

        get("/{groupId}/persons"){
            val groupId = call.parameters["groupId"]
            val personsOfGroupList = fetchPersonsOfGroupEntryPoint(AppContext(dbCon),UUID.fromString(groupId)).getOrElse { throw Exception("Error while showing persons of group") }
            call.respond((personsOfGroupList))
        }
        post("/{groupId}/persons"){
            val groupId = call.parameters["groupId"]
            val personIds = call.receive<List<PersonId>>()
            connectPersonsWithGroupEntryPoint(AppContext(dbCon),UUID.fromString(groupId),personIds)
            call.respondText("Person with PersonIds ${personIds} stored in group with groupId ${groupId}correctly",status = HttpStatusCode.Created)
        }

        post("{/personId}/groups"){
            val personId = call.parameters["personId"]
            val groupIds = call.receive<List<GroupId>>()
            connectGroupsWithPersonEntryPoint(AppContext(dbCon),UUID.fromString(personId),groupIds)
        }

        delete("/{groupId}"){
            println("hello called delete of group person association")
            val groupId = call.parameters["groupId"]
            println(groupId)
            val personIds = call.receive<List<PersonId>>()
            println(personIds)
            removePersonFromGroupEntryPoint(AppContext(dbCon), UUID.fromString(groupId),personIds)
            println("completed")
            call.respondText("person with personId ${personIds} deleted successfully from group with group Id ${groupId}",status = HttpStatusCode.OK)
        }

    }
}