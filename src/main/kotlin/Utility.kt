import java.io.InputStream
import java.net.InetAddress
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine

object Utility {

    val localHostAddress: InetAddress = InetAddress.getLocalHost()

    fun openResourceFile(path: String): InputStream? {
        val context = Thread.currentThread().contextClassLoader
        return context.getResourceAsStream(path)
    }

    fun openAndStartSpeakers(audioFormat: AudioFormat): SourceDataLine {
        val dataLineInfo = DataLine.Info(SourceDataLine::class.java, audioFormat)
        val speakers = AudioSystem.getLine(dataLineInfo) as SourceDataLine
        speakers.open(audioFormat)
        speakers.start()
        return speakers
    }

    /** [WAV file format](https://docs.fileformat.com/audio/wav/) */
    val wavAudioFormat =
        AudioFormat(44100f, 16, 2, true, false)
}
