import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception
import java.net.InetSocketAddress


class Server: WebSocketServer(InetSocketAddress(8885)) {

    var text = ""


    override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        connections.forEach {
            if (it.isOpen){
                it.send(text)
            }
        }
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {

    }

    override fun onMessage(conn: WebSocket, message: String) {
        text = message
        connections.forEach {
            if (it.isOpen){
                it.send(text)
            }
        }
    }

    override fun onError(conn: WebSocket, ex: Exception) {

    }

    override fun onStart() {

    }
}

fun main(){
    Server().start()
}