package ar.edu.unahur.obj2.servidorWeb

open class Analizador {
    val listaRespuestas= mutableListOf<Respuesta>()
    val listaModulos= mutableListOf<Modulo>()
    fun recibeRespuestaServidor(respuesta: Respuesta,modulo: Modulo) {
        listaRespuestas.add(respuesta)
        listaModulos.add(modulo)
    }

}

class AnalizadorIpSospechosa: Analizador() {

}
class AnalizadorEstatico: Analizador(){

}

class AnalizadorDemora: Analizador(){

}