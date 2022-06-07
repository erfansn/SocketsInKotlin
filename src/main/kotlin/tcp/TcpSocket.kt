package tcp

import kotlinx.coroutines.runBlocking

object Server {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        val server = SimpleChatServer()
        server.start()
    }
}

fun main() {
    val client = SimpleChatClient()

    while (true) {
        val text = readLine()!!
        client.sendMessage(text)
        if (text.equals("bye", ignoreCase = true)) {
            client.disconnect()
            break
        }
    }
}
