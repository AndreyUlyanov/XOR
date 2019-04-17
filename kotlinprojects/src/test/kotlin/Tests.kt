package encryption.src

import Encryption
import java.io.File
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EncryptionTests {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    fun second(){
        val result = Encryption("files/test2.txt", "5".toByteArray())
        result.encrypt("files/temp21.txt")
        result.encrypt("files/temp21.txt")
        assertFileContent("files/temp22.txt", "test\n" +
                "test11\n" +
                "test!test")
    }
}