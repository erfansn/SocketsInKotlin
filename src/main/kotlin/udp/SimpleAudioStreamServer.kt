package udp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket

class SimpleAudioStreamServer {

    private val socket = DatagramSocket()

    private val conversationAudio = Utility.openResourceFile("conversation.wav")!!.run {
        readBytes().asList().chunked(BUFFER_SIZE).map(List<Byte>::toByteArray)
    }

    suspend fun start() {
        while (true) {
            for (message in conversationAudio) {
                val datagramPacket = DatagramPacket(
                    message,
                    message.size,
                    Utility.localHostIp,
                    PORT
                )
                withContext(Dispatchers.IO) {
                    socket.send(datagramPacket)
                }
            }
        }
    }
}
