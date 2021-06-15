package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime

// Para no tener los códigos "tirados por ahí", usamos un enum que le da el nombre que corresponde a cada código
// La idea de las clases enumeradas es usar directamente sus objetos: CodigoHTTP.OK, CodigoHTTP.NOT_IMPLEMENTED, etc
enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
  NOT_FOUND(404),
}
class URL(val protocolo: String , val ruta : String, val extension:String ){

}
 class Pedido(val ip: Double, val url: URL, val fechaHora: LocalDateTime){
 fun verificadorHTTP(){
   if(url.protocolo == "http" ){
                                   // Si la url no es http tirar un codigo de respuesta 501
   }
   else{
     print(CodigoHttp.NOT_FOUND)
   }
 }

}




class Respuesta(val codigo: CodigoHttp, val body: String, val tiempo: Int, val pedido: Pedido){


}
