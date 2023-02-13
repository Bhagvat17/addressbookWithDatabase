package com.addressbook

import com.addressbook.models.AppContext
import org.junit.jupiter.api.BeforeAll
open class AppTest{
    companion object {
        lateinit var appCtx: AppContext

        @JvmStatic
        @BeforeAll
        fun setUp(){
            val db = connectToDatabase()
            appCtx = AppContext(db)
            resetDatabase()
        }
    }
}