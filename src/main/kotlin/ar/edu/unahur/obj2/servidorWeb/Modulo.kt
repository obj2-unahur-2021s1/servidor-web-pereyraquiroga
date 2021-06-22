package ar.edu.unahur.obj2.servidorWeb

class Modulo(val extensiones: List<String>,val body: String,val tiempoRespuesta:Int) {

    fun puedeAtenderElPedido(url:String) = extensiones.any{e->url.endsWith(e)}

}

enum class Tipo() {
    TEXTO,IMAGENES,VIDEO
}