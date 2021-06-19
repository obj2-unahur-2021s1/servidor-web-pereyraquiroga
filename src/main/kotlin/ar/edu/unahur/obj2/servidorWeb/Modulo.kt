package ar.edu.unahur.obj2.servidorWeb

class Modulo(val extensiones: List<String>,val tipo:Tipo ,val tiempoRespuesta:Int) {
    fun puedeAtenderElPedido(pedido:Pedido) = extensiones.any{e->pedido.url.endsWith(e)}
}

enum class Extension(val tipo: Tipo) {
    jpg(Tipo.IMAGENES),
    png(Tipo.IMAGENES),
    gif(Tipo.IMAGENES),
    docx(Tipo.TEXTO),
    odt(Tipo.TEXTO),
    txt(Tipo.TEXTO),
    mp4(Tipo.VIDEO)
}

enum class Tipo() {
    TEXTO,IMAGENES,VIDEO
}