package com.addressbook.apis.routes

import com.addressbook.connectToDatabase
import com.addressbook.entrypoints.*
import com.addressbook.models.AppContext
import com.addressbook.requests.AddAddressRequest
import com.addressbook.requests.UpdateAddressRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.addressRouting() {
    val dbCon = connectToDatabase()
    route("/addresses") {
        get {
            val addressList = fetchAllAddressEntryPoint(AppContext(dbCon)).orNull()!!
            call.respond(addressList)
        }
        get("/{personName}") {
            val personName = call.parameters["personName"]
            val address = fetchAddressByPersonNameEntryPoint(AppContext(dbCon), personName!!).orNull()!!
            call.respond(address)
        }
        post(){
            val address = call.receive<AddAddressRequest>()
            var addedAddress = addAddressEntryPoint(AppContext(dbCon),address).orNull()!!
            call.respondText("Address with personID ${address.personId} stored correctly",status = HttpStatusCode.Created)
        }

        delete("/{addressId}"){
            val addressId = call.parameters["addressId"]
            removeAddressByAddressIdEntryPoint(AppContext(dbCon), UUID.fromString(addressId))
            call.respondText("Address with addressId ${addressId} deleted successfully",status = HttpStatusCode.OK)
        }

        put(){
            val address = call.receive<UpdateAddressRequest>()
            updateAddressEntryPoint(AppContext(dbCon),address)
            call.respondText("address with addressId ${address.addressId} updated successfully",status = HttpStatusCode.OK)
        }
    }
}