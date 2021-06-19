package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
  val pedido1=Pedido("198.168.1.5","https://pepito.com.ar/hola.txt", LocalDateTime.now())
  val servidorWeb=ServidorWeb()
   val moduloTexto=Modulo(Tipo.TEXTO,10)
   val moduloVideo=Modulo(Tipo.VIDEO,10)
   val moduloImagen=Modulo(Tipo.IMAGENES,10)
   // EXTENSIONES
   val ListaExtension= mutableListOf<Extension>()

    it("devuelve 501 si recibe una pedido que NO es HTTP"){
      servidorWeb.realizarPedido(pedido1).codigo.shouldBe(CodigoHttp.NOT_IMPLEMENTED)
    }

      it("Modulo puede atender el pedido"){

          moduloTexto.puedeAtenderElPedido(pedido1).shouldBe(true)
      }
    it("Modulo que puede responder al pedido"){
        val  modulos= mutableListOf<Modulo>()
        servidorWeb.agregarModulo(moduloImagen)
        servidorWeb.agregarModulo(moduloTexto)
        servidorWeb.moduloQuePuedeResponderAlPedido(pedido1).shouldBe(true)

    }


  }
})
