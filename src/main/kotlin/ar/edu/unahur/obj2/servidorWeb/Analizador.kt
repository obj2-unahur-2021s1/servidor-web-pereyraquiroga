package ar.edu.unahur.obj2.servidorWeb

abstract class Analizador {

   val modulosRespuestas = mutableMapOf <Modulo,MutableList<Respuesta>>()

    open fun recibeRespuestaServidor(respuesta: Respuesta, modulo: Modulo) {
        modulosRespuestas[modulo]?.add(respuesta)

    }

}

class AnalizadorIpSospechosa(): Analizador() {
    val ipSospechosaYpedidos = mutableMapOf <String,MutableList<Pedido>>()


    fun registrarPedidoIpSospechosa(pedido: Pedido,ipSospechosa:String){
        if(pedido.ip == ipSospechosa ){ // SI LA IP DEL PEDIDO ES LA IP SOSPECHOSA LO AGREGO A LA LISTA DE PEDIDOS DE IP SOSPECHA
            ipSospechosaYpedidos[ipSospechosa]?.add(pedido)
        }
    }

   // fun cuantosPedidosRealizoLaip(ipSospechosa:String)=
}

class AnalizadorDemora(val demoraMinima:Int): Analizador(){

    fun cantidadDeRespuestasDemoradas(modulo: Modulo)= modulosRespuestas[modulo]?.count{it.respuestaDemorada(demoraMinima)}

}

class AnalizadorEstadistica: Analizador(){
    val respuestaAAnalizar = mutableListOf<Respuesta>()

    fun tiempoDeRespuestaPromedio()=respuestaAAnalizar.sumBy { it.tiempo }/respuestaAAnalizar.size




}