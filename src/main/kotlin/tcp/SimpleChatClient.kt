package tcp

import PORT
import Utility.localHostAddress
import java.io.PrintWriter
import java.net.Socket

class SimpleChatClient {

    private val socket = Socket(localHostAddress, PORT)
    private val outputStream = socket.getOutputStream()

    fun sendMessage(text: String) {
        val writer = PrintWriter(outputStream, true)
        writer.println(text)
    }

    fun disconnect() {
        socket.close()
    }
}

