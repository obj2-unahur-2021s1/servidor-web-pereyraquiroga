package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime

// Para no tener los códigos "tirados por ahí", usamos un enum que le da el nombre que corresponde a cada código
// La idea de las clases enumeradas es usar directamente sus objetos: CodigoHTTP.OK, CodigoHTTP.NOT_IMPLEMENTED, etc
enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
  NOT_FOUND(404),
}

class Pedido(val ip: String, val url: String, val fechaHora: LocalDateTime){

  fun protocoloUrl() = url.split(":").first()
  fun rutaUrl() = "/" + url.split(""".[a-z]*/""".toRegex()).last()
  fun extensionUrl() = url.split(".").last()

  }

class Respuesta(val codigo: CodigoHttp, val body: String, val tiempo: Int, val pedido: Pedido)

class ServidorWeb{
val  modulos= mutableListOf<Modulo>()

  fun quitarModulo(modulo:Modulo) {
    modulos.remove(modulo)
  }
  fun agregarModulo(modulo:Modulo){
    modulos.add(modulo)
  }

  fun realizarPedido(pedido: Pedido) :Respuesta {
    if (pedido.url=="http") {
      return Respuesta(CodigoHttp.OK, "",10,pedido)
    }
    if (hayModuloParaElPedido(pedido)){
      val modulito=this.modulos.find {it.puedeAtenderElPedido(pedido.url)}!!
      return Respuesta(CodigoHttp.OK,modulito.body,modulito.tiempoRespuesta,pedido)
    }
    return Respuesta(CodigoHttp.NOT_IMPLEMENTED,  "", 10, pedido)
  }

  fun hayModuloParaElPedido(pedido:Pedido)=this.modulos.any {it.puedeAtenderElPedido(pedido.url)}



}
