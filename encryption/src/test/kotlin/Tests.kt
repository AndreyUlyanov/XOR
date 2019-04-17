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
    fun first(){
        val result = Encryption("files/test1.txt", "1")
        result.encrypt("1", "files/test1.txt", "files/temp1.txt")
        assertFileContent("files/temp1.txt", "[B@443118b0")
        //File("files/temp.txt").delete()
    }

    @Test
    fun second(){
        val result = Encryption("files/temp.txt", "1")
        result.encrypt("1", "files/temp.txt", "files/temp1.txt")
        assertFileContent("files/temp1.txt", "-Hello World!!!\n-Good bye!")
    }
}