import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option

import java.io.FileOutputStream
import java.io.IOException

internal class EncryptionLauncher {

    @Option(name = "-c", metaVar = "EKey", required = true, usage = "Encryption key")
    private val eKey: String? = null

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private val inputFileName: String? = null

    @Argument(required = true, metaVar = "OutputName", index = 1, usage = "Output file name")
    private val outputFileName: String? = null

    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)

        try {
            parser.parseArgument(*args)
        } catch (e: CmdLineException) {
            System.err.println(e.message)
            System.err.println("ciphxor [-c key] inputname.txt outputname.txt")
            parser.printUsage(System.err)
            return
        }


        val key = ByteArray(eKey!!.length)
        if (eKey.length % 2 == 1) {
            System.err.println("Длина клуча должна быть четной.")
            return
        }

        for (i in 0 until eKey.length step 2) key[i] = charsToByte(eKey[i], eKey[i + 1])


        val encryption = Encryption(inputFileName!!, key)
        try {
            val result = encryption.encrypt(outputFileName!!)
            val writer = FileOutputStream(outputFileName)
            writer.write(result)
        } catch (e: IOException) {
            System.err.println(e.message)
        }

    }

    fun charsToByte(c: Char, c1: Char): Byte {
        var result = when (c.toUpperCase()) {
            in '0'..'9' -> (c - '0').toByte()
            in 'A'..'F' -> (c.toUpperCase() - 'A' + 10).toByte()
            else -> throw IllegalArgumentException()
        }
        result = (result + 16 *  when (c1.toUpperCase()) {
            in '0'..'9' -> (c1 - '0')
            in 'A'..'F' -> (c1.toUpperCase() - 'A' + 10)
            else -> throw IllegalArgumentException()
        }).toByte()
        return result
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            EncryptionLauncher().launch(args)
        }
    }

}