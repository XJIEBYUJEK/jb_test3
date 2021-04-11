import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.WebSocket

fun main() {
    val edit = document.getElementById("edit") as HTMLTextAreaElement
    val ws = WebSocket("ws://localhost:8885").apply {

        onmessage = {
            //window.alert(it.data as String)
            edit.value = it.data as String
            Unit
        }
    }
    edit.apply {
        oninput = {
            ws.send(edit.value)
        }
    }

}