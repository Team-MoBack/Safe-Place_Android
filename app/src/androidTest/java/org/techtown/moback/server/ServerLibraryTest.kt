package org.techtown.moback.server

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test

class ServerLibraryTest{

    @Test
    fun registerAndLoginTest(){

        CoroutineScope(Dispatchers.Main).launch{

            var result : Boolean = async(Dispatchers.Default) {
                return@async ServerLibrary.registerUser("test4", "junhyeon", "park", "qkrwns12")
            }.await()

            assertEquals(result, true)

            var token = async(Dispatchers.Default) {
                return@async ServerLibrary.login("test2", "qkrwns12")
            }.await()

            println("token : $token")
        }

    }
}