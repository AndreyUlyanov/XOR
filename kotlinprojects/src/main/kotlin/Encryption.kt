import java.io.*
import kotlin.experimental.xor

class Encryption(private val inputFile: String, private val key: ByteArray) {

    @Throws(IOException::class)
    fun encrypt(outputName: String): ByteArray {
        val reader = File(inputFile).readBytes()
        val writer = ByteArray(reader.size)

        for (i in 0 until reader.size) {
            writer[i] = reader[i] xor (key[i % key.size])
        }

        File(outputName).writeBytes(writer)
        return writer
    }
}