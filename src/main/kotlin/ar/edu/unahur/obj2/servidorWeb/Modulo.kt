package ar.edu.unahur.obj2.servidorWeb

class Modulo(val tipo : Tipo,val tiempoRespuesta:Int) {  // VAL PORQUE ES FIJO
    val extensiones = mutableListOf<Extension>()

    fun puedeAtenderElPedido(pedido:Pedido) = extensiones.any{e->e.toString() == pedido.extensionUrl()}
   // fun agregarExtension(extension:Extension)= extensiones.add(extension)
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