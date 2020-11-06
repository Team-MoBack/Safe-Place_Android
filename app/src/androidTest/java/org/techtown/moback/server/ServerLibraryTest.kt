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

        CoroutineScope(Dispatchers.Default).launch {
            var result = ServerLibrary.searchStoresInRadius(35.8367295, 128.7242246, 500.0,"eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MDQ2ODM1NTksImlhdCI6MTYwNDY2NTU1OSwianRpIjoiY29mZmVla2lsbGVyIn0.nAyNDlVu_2sslTGdjHq4sXPPsduAEe3IJ01lvP7VBgDDqEwIia82c3UUTN6-j-pCY-tYQKonndlkF8MO0LCVjw")

            result.forEach { println(it.toString()) }
        }

    }
}