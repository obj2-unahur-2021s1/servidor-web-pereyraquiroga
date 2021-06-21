package ar.edu.unahur.obj2.servidorWeb

class Modulo(val extensiones: List<String>,val body: String,val tiempoRespuesta:Int) {
    fun puedeAtenderElPedido(url:String) = extensiones.any{e->url.endsWith(e)}
    fun responderAlPedido(pedido: Pedido):Respuesta {
        if (puedeAtenderElPedido(pedido.url)) {
           return Respuesta(CodigoHttp.OK, body, tiempoRespuesta, pedido)
        }
       return Respuesta(CodigoHttp.NOT_FOUND,"",10,pedido)
    }
}

enum class Tipo() {
    TEXTO,IMAGENES,VIDEO
}