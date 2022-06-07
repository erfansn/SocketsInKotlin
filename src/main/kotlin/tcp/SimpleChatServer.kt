package tcp

import PORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.net.ServerSocket
import java.net.Socket

class SimpleChatServer {

    private val socket = ServerSocket(PORT)

    suspend fun start() = coroutineScope {
        launch(Dispatchers.IO) {
            while (true) {
                val client = socket.accept()
                launch {
                    receiveMessages(client)
                }
            }
        }
    }

    private fun receiveMessages(socket: Socket) {
        println("Client ${socket.port} joined")
        while (socket.isConnected) {
            val inputStream = socket.getInputStream()
            val received = inputStream.bufferedReader()
            val message = received.readLine() ?: break

            println("Client ${socket.port}: $message")
        }
        println("Client ${socket.port} left")
        socket.close()
    }
}
