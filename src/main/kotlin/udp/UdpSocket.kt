package udp

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        val broadcaster = SimpleAudioBroadcaster()
        broadcaster.start()
    }
    val listener = SimpleAudioListener()
    listener.listen()
}