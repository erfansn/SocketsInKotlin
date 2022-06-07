package udp

import BUFFER_SIZE
import PORT
import Utility
import Utility.localHostAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket

class SimpleAudioBroadcaster {

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
                    localHostAddress,
                    PORT
                )
                withContext(Dispatchers.IO) {
                    socket.send(datagramPacket)
                }
            }
        }
    }
}
