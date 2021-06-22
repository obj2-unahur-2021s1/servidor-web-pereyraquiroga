package ar.edu.unahur.obj2.servidorWeb

open class Analizador {

   val modulosRespuestas = mutableMapOf <Modulo,MutableList<Respuesta>>()
    fun recibeRespuestaServidor(respuesta: Respuesta,modulo: Modulo) {
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
class AnalizadorEstatico: Analizador(){

}

class AnalizadorDemora(val demoraMinima:Int): Analizador(){

    fun cantidadDeRespuestasDemoradas(modulo: Modulo)= modulosRespuestas[modulo]?.count{it.respuestaDemorada(demoraMinima)}

}

class AnalizadorEstadistico: Analizador(){
    val respuestaAAnalizar = mutableListOf<Respuesta>()

    override fun recibeRespuestaServidor(respuesta :Respuesta, modulo :Modulo){
        respuestaAAnalizar.add(respuesta)
    }

    fun tiempoDeRespuestaPromedio()=respuestaAAnalizar.sumBy { it.tiempo }/respuestaAAnalizar.size


}