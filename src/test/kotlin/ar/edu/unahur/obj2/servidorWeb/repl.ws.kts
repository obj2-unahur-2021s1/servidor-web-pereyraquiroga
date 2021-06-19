import ar.edu.unahur.obj2.servidorWeb.Modulo
import ar.edu.unahur.obj2.servidorWeb.ServidorWeb
import ar.edu.unahur.obj2.servidorWeb.Tipo
import java.time.LocalDateTime

// Pueden usar este archivo para hacer pruebas rápidas,
// de la misma forma en que usaban el REPL de Wollok.

// OJO: lo que esté aquí no será tenido en cuenta
// en la corrección ni reemplaza a los tests.

//listOf(1, 8, 10).average()
val pedido1=Pedido("198.168.1.5","https://pepito.com.ar/hola.txt", LocalDateTime.now())
val servidorWeb= ServidorWeb()
class Pedido(val ip: String, val url: String, val fechaHora: LocalDateTime){
    fun protocoloUrl() = url.split(":").first()
    fun rutaUrl() = "/" + url.split(""".[a-z]*/""".toRegex()).last()
    fun extensionUrl() = url.split(".").last()
}


pedido1.protocoloUrl()
pedido1.rutaUrl()
pedido1.extensionUrl()
//servidorWeb.agregarModulo(moduloImagen)
