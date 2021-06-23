package ar.edu.unahur.obj2.servidorWeb

abstract class Analizador {

    val listaRespuesta= mutableListOf<Respuesta>()
    val listaModulo= mutableListOf<Modulo>()


    open fun recibeRespuestaServidor(respuesta: Respuesta, modulo: Modulo) {

        listaModulo.add(modulo)
        listaRespuesta.add(respuesta)
    }

}

class AnalizadorIpSospechosa(): Analizador() {
    val ipSospechosaYpedidos = mutableMapOf <String,MutableList<Pedido>>()
    val soloPedidosIpSospechosa= mutableListOf<Pedido>()

    fun registrarPedidoIpSospechosa(pedido: Pedido,ipSospechosa:String){

        if(pedido.ip == ipSospechosa ){ // SI LA IP DEL PEDIDO ES LA IP SOSPECHOSA LO AGREGO A LA LISTA DE PEDIDOS DE IP SOSPECHA
            ipSospechosaYpedidos[ipSospechosa]?.add(pedido)
            soloPedidosIpSospechosa.add(pedido)
        }
    }

    fun cuantosPedidosRealizoLaip()=soloPedidosIpSospechosa.size


}

class AnalizadorDemora(val demoraMinima:Int): Analizador(){

    override fun recibeRespuestaServidor(respuesta: Respuesta, modulo: Modulo) {
       if( respuesta.tiempo > demoraMinima){
           modulo.respuestasDemoradas.add(respuesta)
       }
    }
    fun cantidadDeRespuestasDemoradas(modulo: Modulo)= modulo.respuestasDemoradas.size

}

class AnalizadorEstadistica: Analizador(){
    val respuestaAAnalizar = mutableListOf<Respuesta>()

    fun tiempoDeRespuestaPromedio()=respuestaAAnalizar.sumBy { it.tiempo }/respuestaAAnalizar.size




}