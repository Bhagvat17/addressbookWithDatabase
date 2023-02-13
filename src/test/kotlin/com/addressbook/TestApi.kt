package com.addressbook

import com.addressbook.tables.Persons.personId
import com.example.plugins.configureRouting
import com.google.protobuf.Internal
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.util.*
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestApi: AppTest() {
    @OptIn(InternalAPI::class)
    @Test
    fun `add person test`() = testApplication {
        application {
            configureRouting()
        }
        val response1 = client.post("/persons"){
            contentType(ContentType.Application.Json)
            body="""
                {
                "firstName": "Vivek",
                "lastName": "Bhimani"
                }
            """.trimIndent()
        }
        val response2 = client.post("/persons"){
            contentType(ContentType.Application.Json)
            body="""

                {
                "firstName": "Bhagvat",
                "lastName": "Jadeja"
                }

            """.trimIndent()
        }
        val response3 = client.post("/persons"){
            contentType(ContentType.Application.Json)
            body="""
                {
                "firstName": "Parth",
                "lastName": "Raval"
                }

            """.trimIndent()
        }
        val response4 = client.post("/persons"){
            contentType(ContentType.Application.Json)
            body="""
                {
                "firstName": "Hamza",
                "lastName": "Malik"
                }
            """.trimIndent()
        }
        Assertions.assertEquals(HttpStatusCode.Created, response1.status)
        Assertions.assertNotNull(response1.content)
    }


    @OptIn(InternalAPI::class)
    @Test
    fun `list persons test`() = testApplication {
        application {
            configureRouting()
        }

        val personList = client.get("/persons")
        Assertions.assertEquals(HttpStatusCode.OK,personList.status)
        Assertions.assertNotNull(personList)
        println(personList.bodyAsText())
    }

    @OptIn(InternalAPI::class)
    @Test
    fun `fetch a person test`() = testApplication {
        application {
            configureRouting()
        }
        val person = client.get("/persons/Bhagvat")
        Assertions.assertEquals(HttpStatusCode.OK,person.status)
        Assertions.assertNotNull(person)
        println(person.bodyAsText())
    }

    @OptIn(InternalAPI::class)
    @Test
    fun `update person test`() = testApplication {
        application {
            configureRouting()
        }
        val response2_updated = client.put("/persons"){
            contentType(ContentType.Application.Json)
            body="""
                {
                "personId": "5a7224d7-cacb-4618-877e-605200cf91e1",
                "firstName": "Bhagvat",
                "lastName": "Jadeja"
                }

            """.trimIndent()
        }

        Assertions.assertEquals(HttpStatusCode.OK,response2_updated.status)
        Assertions.assertNotNull(response2_updated)
        println(response2_updated.bodyAsText())
        println(client.get("/persons").bodyAsText())
    }

    @OptIn(InternalAPI::class)
    @Test
    fun `delete person test`() = testApplication {
        application {
            configureRouting()
        }
        val response = client.delete("/persons/e041824f-3a4a-4c57-955d-724059c0f757")

        Assertions.assertEquals(HttpStatusCode.OK,response.status)
        println(response.bodyAsText())
        println(client.get("/persons").bodyAsText())
    }

}
